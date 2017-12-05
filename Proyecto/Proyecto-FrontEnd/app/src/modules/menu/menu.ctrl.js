(function ( ng ) {
    let mod = ng.module( 'menuModule' );

    mod.controller( 'menuCtrl', [ '$scope', 'AuthService', '$state', '$http', 'urlBack', 'RolesService', 'SessionService',
        function ( $scope, AuthService, $state, $http, urlBack, RolesService, SessionService ) {
            $scope.menuSrc = 'app/src/modules/menu/empty.html';
            $scope.showMenu = {
                1: false,
                2: false,
                3: false,
                4: false,
                5: false,
                6: false,
                7: false,
            };

            AuthService.checkUser( SessionService.user, undefined )
                       .then( function ( response ) {
                           $scope.user = response;

                           $scope.$apply( function () {
                               $scope.showMenu[ 1 ] = $scope.show( 1 );
                               $scope.showMenu[ 2 ] = $scope.show( 2 );
                               $scope.showMenu[ 3 ] = $scope.show( 3 );
                               $scope.showMenu[ 4 ] = $scope.show( 4 );
                               $scope.showMenu[ 5 ] = $scope.show( 5 );
                               $scope.showMenu[ 6 ] = $scope.show( 6 );
                               $scope.showMenu[ 7 ] = $scope.show( 7 );
                           } );
                       } );

            $http.get( urlBack + '/roles' )
                 .then( function ( response ) {
                     RolesService.roles = response.data;
                 } );

            $scope.show = function ( btnId ) {
                if ( $scope.user ) {
                    switch ( btnId ) {
                        case 1:
                            return $scope.user.roles.some( function ( item ) {
                                return item.name === 'ADMIN' || item.name === 'SYSO';
                            } );
                            break;
                        case 2:
                            return $scope.user.roles.some( function ( item ) {
                                return item.name === 'ADMIN' || item.name === 'SYSO';
                            } );
                            break;
                        case 3:
                            return $scope.user.roles.some( function ( item ) {
                                return item.name === 'ADMIN' || item.name === 'SYSO';
                            } );
                            break;
                        case 4:
                            return true;
                            break;
                        case 5:
                            return true;
                            break;
                        case 6:
                            return $scope.user.roles.some( function ( item ) {
                                return item.name === 'ADMIN' || item.name === 'SYSO';
                            } );
                            break;
                        case 7:
                            return $scope.user.roles.some( function ( item ) {
                                return item.name === 'ADMIN' || item.name === 'SYSO';
                            } );
                            break;
                    }
                }
                return false;
            };

            $scope.goToState = function ( state ) {
                $state.go( state );
            };
            $scope.logout = function () {
                $http( {
                           method: 'POST',
                           headers: {
                               'user': SessionService.user.login,
                               'token': SessionService.user.token
                           },
                           url: urlBack + '/logout'
                       } ).then( function ( response ) {
                    $state.go( 'login' );
                    SessionService.user = undefined;
                } );
            };
        }
    ] );
})( angular );
