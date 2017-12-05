(function ( ng ) {
    let mod = ng.module( 'menuModule', [ 'ui.router' ] );

    mod.config( [ '$stateProvider', '$urlRouterProvider',
                    function ( $stateProvider, $urlRouterProvider ) {
                        // $urlRouterProvider.otherwise( '/login' );

                        $stateProvider
                            .state( 'menu', {
                                url: '/menu',
                                params: {
                                    usuario: null
                                },
                                views: {
                                    'mainView': {
                                        templateUrl: 'app/src/modules/menu/menu.html',
                                        controller: 'menuCtrl'
                                    }
                                }
                            } );
                    } ] );
})( window.angular );
