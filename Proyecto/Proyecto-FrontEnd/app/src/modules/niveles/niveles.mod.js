(function ( ng ) {
    let mod = ng.module( 'nivelesModule', [ 'ui.router' ] );

    mod.config( [ '$stateProvider', '$urlRouterProvider',
                    function ( $stateProvider, $urlRouterProvider ) {
                        $urlRouterProvider.otherwise( '/niveles' );

                        $stateProvider
                            .state( 'niveles', {
                                url: '/niveles',
                                parent: 'menu',
                                views: {
                                    'listView': {
                                        templateUrl: 'app/src/modules/niveles/niveles.html',
                                        controller: 'nivelesCtrl'
                                    }
                                }
                            } )
                            .state( 'nivelesDetail', {
                                url: '/niveles/:idNivel',
                                params: {
                                    idNivel: null,
                                },
                                parent: 'menu',
                                views: {
                                    'listView': {
                                        templateUrl: 'app/src/modules/niveles/detail/niveles.detail.html',
                                        controller: 'nivelDetailCtrl'
                                    }
                                }
                            } );
                    } ] );
})( window.angular );
