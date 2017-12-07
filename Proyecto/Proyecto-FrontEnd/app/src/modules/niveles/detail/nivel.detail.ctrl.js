(function ( ng ) {
    let mod = ng.module( 'nivelesModule' );

    mod.controller( 'nivelDetailCtrl', [ '$scope', '$stateParams', 'AuthService', '$http', 'urlBack', 'SessionService', '$state',
        function ( $scope, $stateParams, AuthService, $http, urlBack, SessionService, $state ) {
            AuthService.checkUser( $scope.$parent.user )
                       .then( function ( response ) {
                           $http( {
                                      method: 'GET',
                                      url: urlBack + '/niveles/' + $stateParams.idNivel,
                                      headers: {
                                          'user': SessionService.user.login,
                                          'token': SessionService.user.token
                                      }
                                  } )
                               .then( function ( response ) {
                                   $scope.nivel = response.data;
                                   $scope.areas = $scope.nivel.areas;

                                   const maxInRow = 10;
                                   let row = -1;
                                   let temp = [];
                                   $scope.areas.forEach( function ( item, index ) {
                                       if ( index % maxInRow === 0 ) {
                                           row++;
                                       }
                                       let nuevo = {
                                           id: item.id,
                                           tipo: item.tipo,
                                           actuadores: item.actuadores,
                                           alertas: item.alertas,
                                           microcontroladores: item.microcontroladores,
                                           x: row,
                                           y: index % maxInRow,
                                           value: 10, //TODO
                                           'hc-a2': 'Area ' + item.id
                                       };
                                       temp.push( nuevo );
                                   } );

                                   $scope.areas = temp;
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
                                               color: '#2cbf39',
                                               name: '< 50'
                                           }, {
                                               from: 51,
                                               to: 100,
                                               color: '#dadf30',
                                               name: '50 - 100'
                                           }, {
                                               from: 101,
                                               color: '#d9483a',
                                               name: '> 100'
                                           } ]
                                       },
                                       tooltip: {
                                           headerFormat: '',
                                           pointFormat: 'Numero de alertas generadas: <b> {point.name}</b> is <b>{point.value}</b>'
                                       },
                                       plotOptions: {
                                           series: {
                                               cursor: 'pointer',
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
                                                           $scope.seeArea( this.id );
                                                       }
                                                   }
                                               }
                                           }
                                       },
                                       series: [ {
                                           name: '',
                                           data: $scope.areas
                                       } ]
                                   };
                                   Highcharts.chart( 'containerArea', info );
                               } );
                       } );

            $scope.seeArea = function ( id ) {
                $state.go( 'areas', { idArea: id } );
            };
        }
    ] );
})( angular );
