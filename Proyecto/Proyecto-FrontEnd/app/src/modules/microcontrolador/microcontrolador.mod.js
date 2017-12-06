(function ( ng ) {
    let mod = ng.module( 'microcontroladorModule', [ 'ui.router' ] );

    mod.config( [ '$stateProvider', '$urlRouterProvider',
                    function ( $stateProvider, $urlRouterProvider ) {
                        $urlRouterProvider.otherwise( '/microcontroladores' );

                        $stateProvider
                            .state( 'microcontroladoresDetail', {
                                url: '/microcontroladores/:idMicrocontrolador',
                                params: {
                                    idMicrocontrolador: null,
                                },
                                parent: 'menu',
                                views: {
                                    'listView': {
                                        templateUrl: 'app/src/modules/microcontrolador/detail/microcontroladores.detail.html',
                                        controller: 'microcontroladorDetailCtrl'
                                    }
                                }
                            } );
                    } ] );
})( window.angular );
