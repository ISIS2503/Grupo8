(function ( ng ) {
    let mod = ng.module( 'usuariosModule', [ 'ui.router' ] );

    mod.config( [ '$stateProvider', '$urlRouterProvider',
                    function ( $stateProvider, $urlRouterProvider ) {
                        $urlRouterProvider.otherwise( '/usuarios' );

                        $stateProvider
                            .state( 'usuarios', {
                                url: '/usuarios',
                                parent: 'menu',
                                views: {
                                    'listView': {
                                        templateUrl: 'app/src/modules/usuarios/usuarios.html',
                                        controller: 'usuariosCtrl'
                                    }
                                }
                            } );
                    } ] );
})( window.angular );
