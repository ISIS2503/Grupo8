(function ( ng ) {
    let mod = ng.module( 'homeModule' );

    mod.controller( 'homeCtrl', [ '$scope', '$state',
        function ( $scope, $state ) {
            $scope.goLogin = function () {
                $state.go( 'login' );
            };
        }
    ] );
})( angular );
