(function () {
    let app = angular.module( 'arquisoftApp', [
        'ui.router',
        'ngCookies',

        'usuariosModule',
        'menuModule',
        'loginModule'
    ] );

    app.controller( 'mainCtrl', [ '$scope',
        function ( $scope ) {
            $scope.dialogsrc = '';
            $scope.modal = {};
        } ] );

    app.constant( 'urlBack', 'http://localhost:9000' );
    app.factory( 'AuthService', [ '$http', '$state', 'urlBack', '$location', function ( $http, $state, urlBack, $location ) {
        return {
            dict: {
                '/menu': [ 'ADMIN', 'SYSO', 'USER' ],
                '/menu/usuarios': [ 'ADMIN', 'SYSO' ],
                '/menu/actuadores': [ 'ADMIN', 'SYSO' ],
                '/menu/alertas': [ 'ADMIN', 'SYSO' ],
                '/menu/niveles': [ 'ADMIN', 'SYSO', 'USER' ],
                '/menu/reportes': [ 'ADMIN', 'SYSO', 'USER' ],
                '/menu/roles': [ 'ADMIN', 'SYSO' ],
                '/menu/variables': [ 'ADMIN', 'SYSO' ],
            },
            verifyRol: function ( user ) {
                let self = this;
                let rta = user.roles.some( r => self.dict[ $location.path() ].indexOf( r.name ) !== -1 );
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
                                         succ( response.data );
                                     }
                                 }
                                 else {
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
                                succ( obj );
                            }
                        }
                        else {
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

    app.config( [ '$qProvider', '$httpProvider', function ( $qProvider, $httpProvider ) {
        $qProvider.errorOnUnhandledRejections( false );
        $httpProvider.defaults.withCredentials = true;
    } ] );
})( window.angular );
