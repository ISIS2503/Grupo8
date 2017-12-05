(function ( ng ) {
    let mod = ng.module( 'nivelesModule' );

    mod.controller( 'nivelDetailCtrl', [ '$scope', '$stateParams',
        function ( $scope, $stateParams ) {
            $scope.nivel = $stateParams.nivel;
        }
    ] );
})( angular );
