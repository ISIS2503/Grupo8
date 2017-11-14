(function ( ng ) {
    let mod = ng.module( 'loginModule' );

    mod.controller( 'loginCtrl', [ '$scope', '$http', 'urlBack', '$state', 'AuthService',
        function ( $scope, $http, urlBack, $state, AuthService ) {
            $scope.logIn = function () {
                $http.post( urlBack + '/login',
                    {
                        username: $scope.username,
                        password: $scope.password
                    } )
                     .then( function ( response ) {
                                let usuario = response.data;
                                $state.go( 'menu', { usuario: usuario } );
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
