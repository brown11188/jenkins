exports.config = {
    seleniumAddress : 'http://localhost:4444/wd/hub',
    baseUrl : 'http://localhost:8080/',
    
    framework: 'jasmine2',

    capabilities : {
        'browserName' : 'firefox'
    },

    specs : [ '*Spec*.js' ],

    rootElement : 'html',
    jasmineNodeOpts : {
        showColors : true,
        defaultTimeoutInterval: 360000,
        isVerbose : true,
        includeStackTrace : true
    }
};
