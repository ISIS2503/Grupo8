(function ( ng ) {
    let mod = ng.module( 'rolesModule', [ 'ui.router' ] );

    mod.config( [ '$stateProvider', '$urlRouterProvider',
                    function ( $stateProvider, $urlRouterProvider ) {
                        $urlRouterProvider.otherwise( '/roles' );

                        $stateProvider
                            .state( 'roles', {
                                url: '/roles',
                                parent: 'menu',
                                views: {
                                    'listView': {
                                        templateUrl: 'app/src/modules/roles/roles.html',
                                        controller: 'rolesCtrl'
                                    }
                                }
                            } );
                    } ] );
})( window.angular );
