(function ( ng ) {
    let mod = ng.module( 'microcontroladorModule' );

    mod.controller( 'microcontroladorDetailCtrl', [ '$scope', '$http', 'SessionService', 'urlBack', 'AuthService', '$state', '$stateParams',
        function ( $scope, $http, SessionService, urlBack, AuthService, $state, $stateParams ) {

            $scope.idMicrocontrolador = $stateParams.idMicrocontrolador;

            let gaugeOptions = {

                chart: {
                    type: 'solidgauge'
                },

                title: null,

                pane: {
                    center: [ '50%', '85%' ],
                    size: '140%',
                    startAngle: -90,
                    endAngle: 90,
                    background: {
                        backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
                        innerRadius: '60%',
                        outerRadius: '100%',
                        shape: 'arc'
                    }
                },

                tooltip: {
                    enabled: false
                },

                // the value axis
                yAxis: {
                    stops: [
                        [ 0.1, '#55BF3B' ], // green
                        [ 0.5, '#DDDF0D' ], // yellow
                        [ 0.9, '#DF5353' ] // red
                    ],
                    lineWidth: 0,
                    minorTickInterval: null,
                    tickAmount: 2,
                    title: {
                        y: -70
                    },
                    labels: {
                        y: 16
                    }
                },

                plotOptions: {
                    solidgauge: {
                        dataLabels: {
                            y: 5,
                            borderWidth: 0,
                            useHTML: true
                        }
                    }
                }
            };

            AuthService.checkUser( $scope.$parent.user )
                       .then( function ( response ) {
                           $http( {
                                      method: 'GET',
                                      headers: {
                                          'user': SessionService.user.login,
                                          'token': SessionService.user.token
                                      },
                                      url: urlBack + '/microcontroladores/' + $scope.idMicrocontrolador
                                  } )
                               .then( function ( response ) {
                                   $scope.microcontrolador = response.data;

                                   $scope.sensores = function () {
                                       $http( {
                                                  method: 'GET',
                                                  headers: {
                                                      'user': SessionService.user.login,
                                                      'token': SessionService.user.token
                                                  },
                                                  url: urlBack + '/microcontroladores/' + $scope.idMicrocontrolador + '/sensores '
                                              } )
                                           .then( function ( response ) {
                                               $scope.sensores = response.data;

                                               Highcharts.chart( 'microcontroladorSensorUno', Highcharts.merge( gaugeOptions, {
                                                   yAxis: {
                                                       min: 0,
                                                       max: 200,
                                                       title: {
                                                           text: $scope.sensores[ 0 ].tipo.nombre
                                                       }
                                                   },

                                                   credits: {
                                                       enabled: false
                                                   },

                                                   series: [ {
                                                       name: $scope.sensores[ 0 ].tipo.nombre,
                                                       data: [ 80 ],
                                                       dataLabels: {
                                                           format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                                                           ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                                                           '<span style="font-size:12px;color:silver">$scope.sensores[0].tipo.uniadadDeMedida</span></div>'
                                                       },
                                                       tooltip: {
                                                           valueSuffix: ' km/h'
                                                       }
                                                   } ]

                                               } ) );
                                               Highcharts.chart( 'microcontroladorSensorDos', Highcharts.merge( gaugeOptions, {
                                                   yAxis: {
                                                       min: 0,
                                                       max: 200,
                                                       title: {
                                                           text: $scope.sensores[ 1 ].tipo.nombre
                                                       }
                                                   },

                                                   credits: {
                                                       enabled: false
                                                   },

                                                   series: [ {
                                                       name: $scope.sensores[ 1 ].tipo.nombre,
                                                       data: [ 80 ],
                                                       dataLabels: {
                                                           format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                                                           ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                                                           '<span style="font-size:12px;color:silver">$scope.sensores[1].tipo.uniadadDeMedida</span></div>'
                                                       },
                                                       tooltip: {
                                                           valueSuffix: ' km/h'
                                                       }
                                                   } ]

                                               } ) );
                                               Highcharts.chart( 'microcontroladorSensorTres', Highcharts.merge( gaugeOptions, {
                                                   yAxis: {
                                                       min: 0,
                                                       max: 200,
                                                       title: {
                                                           text: $scope.sensores[ 2 ].tipo.nombre
                                                       }
                                                   },

                                                   credits: {
                                                       enabled: false
                                                   },

                                                   series: [ {
                                                       name: $scope.sensores[ 2 ].tipo.nombre,
                                                       data: [ 80 ],
                                                       dataLabels: {
                                                           format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                                                           ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                                                           '<span style="font-size:12px;color:silver">$scope.sensores[2].tipo.uniadadDeMedida</span></div>'
                                                       },
                                                       tooltip: {
                                                           valueSuffix: ' km/h'
                                                       }
                                                   } ]

                                               } ) );
                                               Highcharts.chart( 'microcontroladorSensorCuatro', Highcharts.merge( gaugeOptions, {
                                                   yAxis: {
                                                       min: 0,
                                                       max: 200,
                                                       title: {
                                                           text: $scope.sensores[ 3 ].tipo.nombre
                                                       }
                                                   },

                                                   credits: {
                                                       enabled: false
                                                   },

                                                   series: [ {
                                                       name: $scope.sensores[ 3 ].tipo.nombre,
                                                       data: [ 80 ],
                                                       dataLabels: {
                                                           format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                                                           ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                                                           '<span style="font-size:12px;color:silver">$scope.sensores[3].tipo.uniadadDeMedida</span></div>'
                                                       },
                                                       tooltip: {
                                                           valueSuffix: ' km/h'
                                                       }
                                                   } ]

                                               } ) );

                                               Highcharts.chart( 'microcontroladorGraph', {

                                                   title: {
                                                       text: 'Informacion de Sensores'
                                                   },

                                                   subtitle: {
                                                       text: 'Microcontrolador ' + $scope.idMicrocontrolador
                                                   },

                                                   yAxis: {
                                                       title: {
                                                           text: ''
                                                       }
                                                   },
                                                   legend: {
                                                       layout: 'vertical',
                                                       align: 'right',
                                                       verticalAlign: 'middle'
                                                   },

                                                   plotOptions: {
                                                       series: {
                                                           label: {
                                                               connectorAllowed: false
                                                           },
                                                           pointStart: 2010
                                                       }
                                                   },

                                                   series: [ {
                                                       name: $scope.sensores[ 0 ].tipo.nombre,
                                                       data: [ 43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175 ]
                                                   }, {
                                                       name: $scope.sensores[ 1 ].tipo.nombre,
                                                       data: [ 24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434 ]
                                                   }, {
                                                       name: $scope.sensores[ 2 ].tipo.nombre,
                                                       data: [ 11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387 ]
                                                   }, {
                                                       name: $scope.sensores[ 3 ].tipo.nombre,
                                                       data: [ null, null, 7988, 12169, 15112, 22452, 34400, 34227 ]
                                                   } ],

                                                   responsive: {
                                                       rules: [ {
                                                           condition: {
                                                               maxWidth: 500
                                                           },
                                                           chartOptions: {
                                                               legend: {
                                                                   layout: 'horizontal',
                                                                   align: 'center',
                                                                   verticalAlign: 'bottom'
                                                               }
                                                           }
                                                       } ]
                                                   }

                                               } );

                                           } );
                                   };

                                   $scope.sensores();

                               } );
                       } );
        }
    ] );
})( angular );
