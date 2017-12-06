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
                                      url: urlBack + '/minas/niveles'
                                  } )
                               .then( function ( response ) {
                                   $scope.niveles = response.data;
                               } );
                       } );

            $scope.seeNivel = function ( idNivel ) {
                // $http( {
                //            method: 'GET',
                //            headers: {
                //                'user': SessionService.user.login,
                //                'token': SessionService.user.token
                //            },
                //            url: urlBack + '/minas/niveles/' + idNivel
                //        } );


                $scope.niveles.some( function ( item ) {
                    if ( item.id === idNivel ) {
                        $state.go( 'nivelesDetail', { idNivel: item.id, nivel: item } );
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
                    text: 'U.S. states by population in 2016'
                },

                subtitle: {
                    text: 'Source:<a href="https://simple.wikipedia.org/wiki/List_of_U.S._states_by_population">Wikipedia</a>'
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
                        to: 1000000,
                        color: '#F9EDB3',
                        name: '< 1M'
                    }, {
                        from: 1000000,
                        to: 5000000,
                        color: '#FFC428',
                        name: '1M - 5M'
                    }, {
                        from: 5000000,
                        to: 20000000,
                        color: '#FF7987',
                        name: '5M - 20M'
                    }, {
                        from: 20000000,
                        color: '#FF2371',
                        name: '> 20M'
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

                series: [ {
                    'hc-a2': 'NE',
                    name: 'Nebraska',
                    region: 'Midwest',
                    x: 4,
                    y: 4,
                    value: 1881503
                }, {
                    'hc-a2': 'NV',
                    name: 'Nevada',
                    region: 'West',
                    x: 4,
                    y: 2,
                    value: 2839099
                }, {
                    'hc-a2': 'NH',
                    name: 'New Hampshire',
                    region: 'Northeast',
                    x: 1,
                    y: 11,
                    value: 1326813
                }, {
                    'hc-a2': 'NJ',
                    name: 'New Jersey',
                    region: 'Northeast',
                    x: 3,
                    y: 10,
                    value: 8944469
                }, {
                    'hc-a2': 'NM',
                    name: 'New Mexico',
                    region: 'West',
                    x: 6,
                    y: 3,
                    value: 2085572
                }, {
                    'hc-a2': 'NY',
                    name: 'New York',
                    region: 'Northeast',
                    x: 2,
                    y: 9,
                    value: 19745289
                }, {
                    'hc-a2': 'NC',
                    name: 'North Carolina',
                    region: 'South',
                    x: 5,
                    y: 9,
                    value: 10146788
                }, {
                    'hc-a2': 'ND',
                    name: 'North Dakota',
                    region: 'Midwest',
                    x: 2,
                    y: 3,
                    value: 739482
                }, {
                    'hc-a2': 'OH',
                    name: 'Ohio',
                    region: 'Midwest',
                    x: 3,
                    y: 8,
                    value: 11614373
                }, {
                    'hc-a2': 'OK',
                    name: 'Oklahoma',
                    region: 'South',
                    x: 6,
                    y: 4,
                    value: 3878051
                }, {
                    'hc-a2': 'OR',
                    name: 'Oregon',
                    region: 'West',
                    x: 4,
                    y: 1,
                    value: 3970239
                }, {
                    'hc-a2': 'PA',
                    name: 'Pennsylvania',
                    region: 'Northeast',
                    x: 3,
                    y: 9,
                    value: 12784227
                }, {
                    'hc-a2': 'RI',
                    name: 'Rhode Island',
                    region: 'Northeast',
                    x: 2,
                    y: 11,
                    value: 1055173
                }, {
                    'hc-a2': 'SC',
                    name: 'South Carolina',
                    region: 'South',
                    x: 6,
                    y: 8,
                    value: 4832482
                }, {
                    'hc-a2': 'SD',
                    name: 'South Dakota',
                    region: 'Midwest',
                    x: 3,
                    y: 4,
                    value: 853175
                }, {
                    'hc-a2': 'TN',
                    name: 'Tennessee',
                    region: 'South',
                    x: 5,
                    y: 7,
                    value: 6651194
                }, {
                    'hc-a2': 'TX',
                    name: 'Texas',
                    region: 'South',
                    x: 7,
                    y: 4,
                    value: 27862596
                }, {
                    'hc-a2': 'UT',
                    name: 'Utah',
                    region: 'West',
                    x: 5,
                    y: 4,
                    value: 2942902
                }, {
                    'hc-a2': 'VT',
                    name: 'Vermont',
                    region: 'Northeast',
                    x: 1,
                    y: 10,
                    value: 626011
                }, {
                    'hc-a2': 'VA',
                    name: 'Virginia',
                    region: 'South',
                    x: 5,
                    y: 8,
                    value: 8411808
                }, {
                    'hc-a2': 'WA',
                    name: 'Washington',
                    region: 'West',
                    x: 2,
                    y: 1,
                    value: 7288000
                }, {
                    'hc-a2': 'WV',
                    name: 'West Virginia',
                    region: 'South',
                    x: 4,
                    y: 7,
                    value: 1850326
                }, {
                    'hc-a2': 'WI',
                    name: 'Wisconsin',
                    region: 'Midwest',
                    x: 2,
                    y: 5,
                    value: 5778708
                }, {
                    'hc-a2': 'WY',
                    name: 'Wyoming',
                    region: 'West',
                    x: 3,
                    y: 3,
                    value: 584153
                }]
            });


        }
    ] );
})( angular );
