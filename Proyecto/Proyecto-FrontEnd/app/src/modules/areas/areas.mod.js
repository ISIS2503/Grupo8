(function ( ng ) {
    let mod = ng.module( 'areaModule', [ 'ui.router' ] );

    mod.config( [ '$stateProvider', '$urlRouterProvider',
                    function ( $stateProvider, $urlRouterProvider ) {
                        $urlRouterProvider.otherwise( '/areas' );

                        $stateProvider
                            .state( 'areas', {
                                url: '/areas/:idArea',
                                parent: 'menu',
                                params: {
                                    idArea: null
                                },
                                views: {
                                    'listView': {
                                        templateUrl: 'app/src/modules/areas/areas.html',
                                        controller: 'areasCtrl'
                                    }
                                }
                            } );
                    } ] );
})( window.angular );
