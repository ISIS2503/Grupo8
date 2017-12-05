(function ( ng ) {
    let mod = ng.module( 'usuariosModule' );

    mod.controller( 'usuariosCtrl', [ '$scope', '$http', 'urlBack', 'RolesService', 'AuthService',
        function ( $scope, $http, urlBack, RolesService, AuthService ) {
            AuthService.checkUser( $scope.$parent.user )
                       .then( function ( response ) {
                           $http.get( urlBack + '/usuarios' )
                                .then( function ( response ) {
                                    $scope.usuariosRecords = response.data;
                                } );
                       } );

            $scope.delete = function ( id ) {
                $http.delete( urlBack + '/usuarios/' + id )
                     .then( function ( response ) {
                         let user = $scope.usuariosRecords.find( function ( item ) {
                             return item.id === id;
                         } );
                         $scope.usuariosRecords.splice( $scope.usuariosRecords.indexOf( user ), 1 );
                     } );
            };
            $scope.edit = function ( usuario ) {
                $scope.$parent.$parent.modal = new function () {
                    let self = this;
                    this.currentUsuario = JSON.parse( JSON.stringify( usuario ) );
                    this.removeRolFrom = function ( usuario, rol ) {
                        usuario.roles.splice( usuario.roles.indexOf( rol ), 1 );
                        this.availableRoles = calculateRols();
                    };
                    this.availableRoles = calculateRols();
                    this.addRol = false;
                    this.selectedRol = undefined;
                    this.letsAddRol = function () {
                        this.addRol = true;
                    };
                    this.selectRol = function () {
                        if ( this.selectedRol ) {
                            this.addRol = false;
                            this.currentUsuario.roles.push( RolesService.roles[ this.selectedRol - 1 ] );
                            this.availableRoles = calculateRols();
                        }
                    };
                    this.save = function () {
                        $http.put( urlBack + '/usuarios/' + usuario.id, this.currentUsuario )
                             .then( function () {
                                 let ind = $scope.usuariosRecords.indexOf( usuario );
                                 $scope.usuariosRecords[ ind ] = self.currentUsuario;
                                 $( '#modal' ).modal( 'hide' );
                             }, response => self.errorModal = response.data );
                    };
                    this.errorModal = undefined;

                    function calculateRols() {
                        return RolesService.roles.filter( function ( item ) {
                            return !self.currentUsuario.roles.some( function ( item2 ) {
                                return item2.name === item.name;
                            } );
                        } );
                    }
                };
                $scope.$parent.$parent.dialogsrc = 'app/src/modules/usuarios/usuario.edit.html';
                $( '#modal' ).modal( 'show' );
            };
            $scope.create = function () {

                $scope.$parent.$parent.modal = new function () {
                    let self = this;
                    this.currentUsuario = {
                        login: '',
                        password: '',
                        roles: []
                    };
                    this.removeRolFrom = function ( usuario, rol ) {
                        usuario.roles.splice( usuario.roles.indexOf( rol ), 1 );
                        this.availableRoles = calculateRols();
                    };
                    this.availableRoles = calculateRols();
                    this.addRol = false;
                    this.selectedRol = undefined;
                    this.letsAddRol = function () {
                        this.addRol = true;
                    };
                    this.selectRol = function () {
                        if ( this.selectedRol ) {
                            this.addRol = false;
                            this.currentUsuario.roles.push( RolesService.roles[ this.selectedRol - 1 ] );
                            this.availableRoles = calculateRols();
                        }
                    };
                    this.save = function () {
                        $http.post( urlBack + '/usuarios', this.currentUsuario )
                             .then( function ( response ) {
                                 $scope.usuariosRecords.push( response.data );
                                 $( '#modal' ).modal( 'hide' );
                             }, function ( response ) {
                                 self.errorModal = response.data;
                             } );
                    };
                    this.errorModal = undefined;

                    function calculateRols() {
                        return RolesService.roles.filter( function ( item ) {
                            return !self.currentUsuario.roles.some( function ( item2 ) {
                                return item2.name === item.name;
                            } );
                        } );
                    }
                };


                $scope.$parent.$parent.dialogsrc = 'app/src/modules/usuarios/usuario.create.html';
                $( '#modal' ).modal( 'show' );
            };
        }
    ] );
})( angular );
