(function ( ng ) {
    let mod = ng.module( 'microcontroladorModule' );

    mod.controller( 'microcontroladorDetailCtrl', [ '$scope', '$http', 'SessionService', 'urlBack', 'AuthService', '$state', '$stateParams',
        function ( $scope, $http, SessionService, urlBack, AuthService, $state, $stateParams ) {

            $scope.idMicrocontrolador = $stateParams.idMicrocontrolador;

            let gaugeOptions = {

                chart: {
                    type: 'solidgauge',
                    backgroundColor: '#FFFF'
                },

                title: null,

                pane: {
                    center: [ '50%', '85%' ],
                    size: '140%',
                    startAngle: -90,
                    endAngle: 90,
                    background: {
                        backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEEE',
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
                        [ 0.1, '#2cbf39' ], // green
                        [ 0.5, '#dadf30' ], // yellow
                        [ 0.9, '#d9483a' ] // red
                    ],
                    lineWidth: 0,
                    minorTickInterval: null,
                    tickAmount: 2,
                    title: {
                        y: 70
                    },
                    labels: {
                        y: 16,
                        style:{
                            fontSize:'16px'
                        }
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

                                       $scope.sensors = [];

                                       for(var i = 0; i < $scope.sensores.length; i = i+1){
                                           var datos = []
                                           for(var j = 0; j < $scope.sensores[i].datos.length; j = j+1){
                                               datos.push([$scope.sensores[i].datos[j].timeStamp, j]);//$scope.sensores[0].datos[j].valor
                                           }
                                           $scope.sensors.push(datos);
                                       }

                                       Highcharts.chart( 'microcontroladorSensorUno', Highcharts.merge( gaugeOptions, {
                                           yAxis: {
                                               min: $scope.sensores[ 0 ].minimo,
                                               max: $scope.sensores[ 0 ].maximo * 2,
                                               title: {
                                                   text: $scope.sensores[ 0 ].tipo.nombre,
                                                   style:{
                                                       color: '#1573a9'
                                                   }
                                               }
                                           },

                                           credits: {
                                               enabled: false
                                           },

                                           series: [ {
                                               name: $scope.sensores[ 0 ].tipo.nombre,
                                               data: [$scope.sensors[ 0 ][$scope.sensors[ 0 ].length-1][1]],
                                               dataLabels: {
                                                   format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                                                   ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                                                   '<span style="font-size:14px;color:#7d7d7d">'+$scope.sensores[0].tipo.uniadadDeMedida+'</span></div>'
                                   },
                                               tooltip: {
                                                   valueSuffix: $scope.sensores[0].tipo.uniadadDeMedida
                                               }
                                           } ]

                                       } ) );
                                       Highcharts.chart( 'microcontroladorSensorDos', Highcharts.merge( gaugeOptions, {
                                           yAxis: {
                                               min: $scope.sensores[ 1 ].minimo,
                                               max: $scope.sensores[ 1 ].maximo * 2,
                                               title: {
                                                   text: $scope.sensores[ 1 ].tipo.nombre,
                                                   style:{
                                                       color: '#1573a9'
                                                   }
                                               }
                                           },

                                           credits: {
                                               enabled: false
                                           },

                                           series: [ {
                                               name: $scope.sensores[ 1 ].tipo.nombre,
                                               data:[$scope.sensors[ 2 ][$scope.sensors[ 1 ].length-1][1]],
                                               dataLabels: {
                                                   format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                                                   ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                                                   '<span style="font-size:14px;color:#7d7d7d">'+$scope.sensores[1].tipo.uniadadDeMedida+'</span></div>'
                                               },
                                               tooltip: {
                                                   valueSuffix: $scope.sensores[1].tipo.uniadadDeMedida
                                               }
                                           } ]

                                       } ) );
                                       Highcharts.chart( 'microcontroladorSensorTres', Highcharts.merge( gaugeOptions, {
                                           yAxis: {
                                               min: $scope.sensores[ 2 ].minimo,
                                               max: $scope.sensores[ 2 ].maximo * 2,
                                               title: {
                                                   text: $scope.sensores[ 2 ].tipo.nombre,
                                                   style:{
                                                       color: '#1573a9'
                                                   }
                                               }
                                           },

                                           credits: {
                                               enabled: false
                                           },

                                           series: [ {
                                               name: $scope.sensores[ 2 ].tipo.nombre,
                                               data: [$scope.sensors[ 2 ][$scope.sensors[ 2 ].length-1][1]],
                                               dataLabels: {
                                                   format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                                                   ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                                                   '<span style="font-size:14px;color:#7d7d7d">'+$scope.sensores[2].tipo.uniadadDeMedida+'</span></div>'
                                               },
                                               tooltip: {
                                                   valueSuffix: $scope.sensores[2].tipo.uniadadDeMedida
                                               }
                                           } ]

                                       } ) );
                                       Highcharts.chart( 'microcontroladorSensorCuatro', Highcharts.merge( gaugeOptions, {
                                           yAxis: {
                                               min: $scope.sensores[ 3 ].minimo,
                                               max: $scope.sensores[ 3 ].maximo * 2,
                                               title: {
                                                   text: $scope.sensores[ 3 ].tipo.nombre,
                                                   style:{
                                                       color: '#1573a9'
                                                   }
                                               }
                                           },

                                           credits: {
                                               enabled: false
                                           },

                                           series: [ {
                                               name: $scope.sensores[ 3 ].tipo.nombre,
                                               data: [$scope.sensors[ 3 ][$scope.sensors[ 3 ].length-1][1]],
                                               dataLabels: {
                                                   format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                                                   ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                                                   '<span style="font-size:14px;color:#7d7d7d">'+$scope.sensores[3].tipo.uniadadDeMedida+'</span></div>'
                                               },
                                               tooltip: {
                                                   valueSuffix: $scope.sensores[3].tipo.uniadadDeMedida
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

                                           xAxis: {
                                               type: 'datetime',
                                               labels: {
                                                   format: '{value:%H:%M:%S}'
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
                                                   }
                                               }
                                           },

                                           series: [ {
                                               name: $scope.sensores[ 0 ].tipo.nombre,
                                               data: $scope.sensors[ 0 ]
                                           }, {
                                               name: $scope.sensores[ 1 ].tipo.nombre,
                                               data: $scope.sensors[ 1 ]
                                           }, {
                                               name: $scope.sensores[ 2 ].tipo.nombre,
                                               data: $scope.sensors[ 2 ]
                                           }, {
                                               name: $scope.sensores[ 3 ].tipo.nombre,
                                               data: $scope.sensors[ 3 ]
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


                                   $scope.sensores();

                               } );
                       } );
        }
    ] );
})( angular );
