(function ( ng ) {
    let mod = ng.module( 'loginModule', [ 'ui.router' ] );

    mod.config( [ '$stateProvider', '$urlRouterProvider',
                    function ( $stateProvider, $urlRouterProvider ) {
                        $urlRouterProvider.otherwise( '/login' );

                        $stateProvider
                            .state( 'login', {
                                url: '/login',
                                views: {
                                    'mainView': {
                                        templateUrl: 'app/src/modules/login/login.html',
                                        controller: 'loginCtrl'
                                    }
                                }
                            } )
                            .state( 'unauthorized', {
                                url: '/unauthorized',
                                views: {
                                    'mainView': {
                                        templateUrl: 'app/src/modules/login/unauthorized.html',
                                        controller: [ '$scope', '$state',
                                            function ( $scope, $state ) {
                                                $scope.goLogin = function () {
                                                    $state.go( 'login' );
                                                };
                                            } ]
                                    }
                                }
                            } );
                    } ] );
})( window.angular );
