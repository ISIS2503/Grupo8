(function ( ng ) {
    let mod = ng.module( 'nivelesModule' );

    mod.controller( 'nivelesCtrl', [ '$scope', '$http', 'SessionService', 'urlBack', 'AuthService', '$state',
        function ( $scope, $http, SessionService, urlBack, AuthService, $state ) {

            AuthService.checkUser( $scope.$parent.user )
                       .then( function ( response ) {
                           $http( {
                                      method: 'GET',
                                      headers: {
                                          'user': SessionService.user.login,
                                          'token': SessionService.user.token
                                      },
                                      url: urlBack + '/minas/niveles'
                                  } )
                               .then( function ( response ) {
                                   $scope.niveles = response.data;
                               } );
                       } );

            $scope.seeNivel = function ( idNivel ) {
                // $http( {
                //            method: 'GET',
                //            headers: {
                //                'user': SessionService.user.login,
                //                'token': SessionService.user.token
                //            },
                //            url: urlBack + '/minas/niveles/' + idNivel
                //        } );

                $scope.niveles.some( function ( item ) {
                    if ( item.id = idNivel ) {
                        $state.go( 'nivelesDetail', { nivel: item } );
                        return true;
                    }
                } );

            };

        }
    ] );
})( angular );
