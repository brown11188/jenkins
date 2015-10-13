var app = angular.module("breaApp", [ "ngRoute", 'ui.bootstrap', 'pascalprecht.translate', 'ngCookies','ngAnimate' ]);
app.config(function($translateProvider, $translatePartialLoaderProvider) {
    $translateProvider.useLoader('$translatePartialLoader', {
        urlTemplate : 'resources/i18n/{part}-{lang}.json'
    });
    $translateProvider.preferredLanguage('en');
    $translateProvider.fallbackLanguage('en');
    $translateProvider.useCookieStorage();
});
app.run(function($rootScope, $translate) {
    $rootScope.$on('$translatePartialLoaderStructureChanged', function() {
        $translate.refresh();
    });
});