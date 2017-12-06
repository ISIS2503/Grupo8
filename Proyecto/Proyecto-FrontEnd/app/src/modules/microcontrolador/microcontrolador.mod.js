(function ( ng ) {
    let mod = ng.module( 'microcontroladorModule', [ 'ui.router' ] );

    mod.config( [ '$stateProvider', '$urlRouterProvider',
                    function ( $stateProvider, $urlRouterProvider ) {
                        $urlRouterProvider.otherwise( '/microcontroladores' );

                        $stateProvider
                            .state( 'microcontrolador', {
                                url: '/microcontroladores',
                                parent: 'menu',
                                views: {
                                    'listView': {
                                        templateUrl: 'app/src/modules/microcontrolador/microcontroladores.html',
                                        controller: 'microcontroladoresCtrl'
                                    }
                                }
                            } )
                            .state( 'microcontroladoresDetail', {
                                url: '/microcontroladores/:idmicro',
                                params: {
                                    idmicro: null,
                                },
                                parent: 'menu',
                                views: {
                                    'listView': {
                                        templateUrl: 'app/src/modules/microcontroladores/detail/microcontrolador.detail.html',
                                        controller: 'microcontroladorDetailCtrl'
                                    }
                                }
                            } );
                    } ] );
})( window.angular );
