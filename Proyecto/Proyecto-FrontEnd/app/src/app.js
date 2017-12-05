(function () {
    let app = angular.module( 'arquisoftApp', [
        'ui.router',
        'ngCookies',

        'variablesModule',
        'rolesModule',
        'nivelesModule',
        'usuariosModule',
        'menuModule',
        'loginModule',
        'homeModule',
    ] );

    app.controller( 'mainCtrl', [ '$scope',
        function ( $scope ) {
            $scope.dialogsrc = '';
            $scope.modal = {};
        } ] );

    app.constant( 'urlBack', 'http://localhost:9000' );
    app.factory( 'AuthService', [ '$http', '$state', 'urlBack', '$location', 'SessionService', function ( $http, $state, urlBack, $location, SessionService ) {
        return {
            dict: {
                '/menu$': [ 'ADMIN', 'SYSO', 'USER' ],
                '/menu/usuarios$': [ 'ADMIN', 'SYSO' ],
                '/menu/actuadores$': [ 'ADMIN', 'SYSO' ],
                '/menu/alertas$': [ 'ADMIN', 'SYSO' ],
                '/menu/niveles$': [ 'ADMIN', 'SYSO', 'USER' ],
                '/menu/reportes$': [ 'ADMIN', 'SYSO', 'USER' ],
                '/menu/roles$': [ 'ADMIN', 'SYSO' ],
                '/menu/variables$': [ 'ADMIN', 'SYSO' ],
                '/menu/niveles/[0-9]+$': [ 'ADMIN', 'SYSO' ],
            },
            verifyRol: function ( user ) {
                let self = this;

                let rta = false;
                Object.keys( self.dict ).map( p => new RegExp( p, 'g' ) ).forEach( function ( item, index ) {
                    if ( item.test( $location.path() ) ) {
                        rta = user.roles.some( r => self.dict[ Object.keys( self.dict )[ index ] ] );
                    }
                } );
                if ( !rta ) {
                    $state.go( 'unauthorized' );
                    return undefined;
                }
                return user;
            },
            checkUser: function ( obj, dontVerify ) {
                let self = this;
                return new Promise( function ( succ, err ) {
                    if ( !obj ) {
                        $http.get( urlBack + '/actual' )
                             .then( function ( response ) {
                                 if ( !dontVerify ) {
                                     if ( self.verifyRol( response.data ) ) {
                                         SessionService.user = response.data;
                                         succ( response.data );
                                     }
                                 }
                                 else {
                                     SessionService.user = response.data;
                                     succ( response.data );
                                 }
                             }, function () {
                                 if ( !dontVerify ) {
                                     $state.go( 'unauthorized' );
                                     err( 'unauthorized' );
                                 }
                                 else {
                                     succ( undefined );
                                 }
                             } );
                    }
                    else {
                        if ( !dontVerify ) {
                            if ( self.verifyRol( obj ) ) {
                                SessionService.user = obj;
                                succ( obj );
                            }
                        }
                        else {
                            SessionService.user = obj;
                            succ( obj );
                        }
                    }
                } );
            }
        };
    } ] );
    app.factory( 'RolesService', function () {
        return {
            roles: []
        };
    } );
    app.factory( 'SessionService', function () {
        return {
            user: undefined
        };
    } );

    app.config( [ '$qProvider', '$httpProvider', function ( $qProvider, $httpProvider ) {
        $qProvider.errorOnUnhandledRejections( false );
        $httpProvider.defaults.withCredentials = true;
    } ] );
})( window.angular );
