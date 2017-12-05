(function ( ng ) {
    let mod = ng.module( 'variablesModule', [ 'ui.router' ] );

    mod.config( [ '$stateProvider', '$urlRouterProvider',
                    function ( $stateProvider, $urlRouterProvider ) {
                        $urlRouterProvider.otherwise( '/variables' );

                        $stateProvider
                            .state( 'variables', {
                                url: '/variables',
                                parent: 'menu',
                                views: {
                                    'listView': {
                                        templateUrl: 'app/src/modules/variables/variables.html',
                                        controller: 'variablesCtrl'
                                    }
                                }
                            } );
                    } ] );
})( window.angular );
