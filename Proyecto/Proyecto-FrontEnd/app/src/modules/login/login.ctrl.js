(function ( ng ) {
    let mod = ng.module( 'loginModule' );

    mod.controller( 'loginCtrl', [ '$scope', '$http', 'urlBack', '$state', 'AuthService', 'SessionService',
        function ( $scope, $http, urlBack, $state, AuthService, SessionService ) {
            $scope.logIn = function () {
                $http.post( urlBack + '/login',
                    {
                        login: $scope.login,
                        password: $scope.password
                    } )
                     .then( function ( response ) {
                                SessionService.user = response.data;
                                $state.go( 'menu' );
                            },
                            function ( response ) {
                                $scope.loginerror = response.data;
                            } );
            };

            AuthService.checkUser( undefined, true )
                       .then( function ( response ) {
                           if ( response && response.roles.length !== 0 ) {
                               console.log( response );
                               $state.go( 'menu' );
                           }
                       } );
        }
    ] );
})( angular );
