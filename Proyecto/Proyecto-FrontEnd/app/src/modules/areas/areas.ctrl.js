(function ( ng ) {
    let mod = ng.module( 'areaModule' );

    mod.controller( 'areasCtrl', [ '$scope', '$stateParams', 'AuthService', 'SessionService', '$http', 'urlBack', '$state',
        function ( $scope, $stateParams, AuthService, SessionService, $http, urlBack, $state ) {
            AuthService.checkUser( $scope.$parent.user )
                       .then( function ( response ) {
                           $http( {
                                      method: 'GET',
                                      url: urlBack + '/areas/' + $stateParams.idArea,
                                      headers: {
                                          'user': SessionService.user.login,
                                          'token': SessionService.user.token
                                      }
                                  } )
                               .then( function ( response ) {
                                   $scope.area = response.data;
                                   $scope.microcontroladores = $scope.area.microcontroladores;

                                   const maxInRow = 10;
                                   let row = -1;
                                   let temp = [];
                                   $scope.microcontroladores.forEach( function ( item, index ) {
                                       if ( index % maxInRow === 0 ) {
                                           row++;
                                       }
                                       let nuevo = {
                                           id: item.id,
                                           sensores: item.sensores,
                                           x: row,
                                           y: index % maxInRow,
                                           value: 10, //TODO
                                           'hc-a2': 'MicronControlador ' + item.id
                                       };
                                       temp.push( nuevo );
                                   } );

                                   $scope.microcontroladores = temp;
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
                                                           $scope.seeMC( this.id );
                                                       }
                                                   }
                                               }
                                           }
                                       },
                                       series: [ {
                                           name: '',
                                           data: $scope.microcontroladores
                                       } ]
                                   };
                                   Highcharts.chart( 'containerMicrocontrolador', info );
                               } );
                       } );
            $scope.seeMC = function ( id ) {
                $state.go( 'microcontroladoresDetail', { idMicrocontrolador: id } );
            };
        }
    ] );
})( angular );
