(function ( ng ) {
    let mod = ng.module( 'microcontroladorModule' );

    mod.controller( 'microcontroladoresCtrl', [ '$scope', '$http', 'SessionService', 'urlBack', 'AuthService', '$state','$stateParams',
        function ( $scope, $http, SessionService, urlBack, AuthService, $state, $stateParams ) {

            AuthService.checkUser( $scope.$parent.user )
                       .then( function ( response ) {
                           $http( {
                                      method: 'GET',
                                      headers: {
                                          'user': SessionService.user.login,
                                          'token': SessionService.user.token
                                      },
                                      url: urlBack + '/microcontroladores'
                                  } )
                               .then( function ( response ) {
                                   $scope.microcontroladores = response.data;
                               } );
                       } );

            $scope.seemicrocontrolador = function ( idmicrocontrolador ) {


                $scope.microcontroladores.some( function ( item ) {
                    if ( item.id === idmicrocontrolador ) {
                        $state.go( 'microcontroladoresDetail', { idmicrocontrolador: item.id, microcontrolador: item } );
                        return true;
                    }
                } );

            };

            Highcharts.chart('container', {
                chart: {
                    type: 'tilemap',
                    inverted: true,
                    height: '80%'
                },

                title: {
                    text: 'microcontroladores'
                },

                subtitle: {
                   // text: 'Source:<a href="https://simple.wikipedia.org/wiki/List_of_U.S._states_by_population">Wikipedia</a>'
                },

                xAxis: {
                    visible: false
                },

                yAxis: {
                    visible: false
                },

                colorAxis: {
                    dataClasses: [{
                        from: 0,
                        to: 50,
                        color: '#FFC428',
                        name: '< 50'
                    }, {
                        from: 51,
                        to: 100,
                        color: '#FF7987',
                        name: '50 - 100'
                    }, {
                        from: 101,
                        color: '#FF2371',
                        name: '> 100'
                    }]
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
                        }
                    }
                },

                series: [{
                    name: '',
                    data: [{
                        'hc-a2': '4',
                        name: 'Alabama',
                        region: 'South',
                        x: 0,
                        y: 0,
                        value: 4849377
                    }, {
                        'hc-a2': '67',
                        name: 'Alaska',
                        region: 'West',
                        x: 0,
                        y: 1,
                        value: 737732
                    }, {
                        'hc-a2': '105',
                        name: 'Arizona',
                        region: 'West',
                        x: 0,
                        y: 2,
                        value: 6745408
                    },]
                }]
            });


        }
    ] );
})( angular );
