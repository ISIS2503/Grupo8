(function ( ng ) {
    let mod = ng.module( 'nivelesModule' );

    mod.controller( 'nivelesCtrl', [ '$scope', '$http', 'SessionService', 'urlBack', 'AuthService', '$state',
        function ( $scope, $http, SessionService, urlBack, AuthService, $state ) {

            AuthService.checkUser( $scope.$parent.user )
                       .then( function ( response ) {
                           $http( {
                                      method: 'GET',
                                      headers: {
                                          'user': SessionService.user.login,
                                          'token': SessionService.user.token
                                      },
                                      url: urlBack + '/niveles'
                                  } )
                               .then( function ( response ) {
                                   $scope.niveles = response.data;
                                   let temp = [];
                                   $scope.niveles.forEach( function ( item, index ) {
                                       let nuevo = {
                                           areas: item.areas,
                                           id: item.id,
                                           nivel: item.nivel,
                                           reportes: item.reportes,
                                           x: 0,
                                           y: index,
                                           value: 10,
                                           'hc-a2': 'Nivel ' + item.id
                                       };
                                       temp.push( nuevo );
                                   } );
                                   $scope.niveles = temp;

                                   let info = {
                                       chart: {
                                           type: 'tilemap',
                                           inverted: true,
                                           // height: '50%',
                                       },
                                       title: {
                                           text: 'Niveles Del Sistema'
                                       },
                                       xAxis: {
                                           visible: false
                                       },
                                       yAxis: {
                                           visible: false
                                       },
                                       colorAxis: {
                                           dataClasses: [ {
                                               from: 0,
                                               to: 50,
                                               color: '#F9EDB3',
                                               name: '< 50'
                                           }, {
                                               from: 51,
                                               to: 100,
                                               color: '#FFC428',
                                               name: '50 - 100'
                                           }, {
                                               from: 101,
                                               color: '#FF7987',
                                               name: '> 100'
                                           } ]
                                       },
                                       tooltip: {
                                           headerFormat: '',
                                           pointFormat: 'The population of <b> {point.name}</b> is <b>{point.value}</b>'
                                       },
                                       plotOptions: {
                                           series: {
                                               dataLabels: {
                                                   enabled: true,
                                                   format: '{point.hc-a2}',
                                                   color: '#000000',
                                                   style: {
                                                       textOutline: false
                                                   }
                                               },
                                               point: {
                                                   events: {
                                                       click: function () {
                                                           $scope.seeNivel( this.id );
                                                       }
                                                   }
                                               }
                                           }
                                       },
                                       series: [ {
                                           name: '',
                                           data: $scope.niveles
                                       } ]
                                   };
                                   Highcharts.chart( 'container', info );
                               } );
                       } );

            $scope.seeNivel = function ( idNivel ) {
                $state.go( 'nivelesDetail', { idNivel: idNivel } );
                return true;
            };
        }
    ] );
})( angular );
