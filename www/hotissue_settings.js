var Settings = function() {
};

Settings.prototype.show = function(successCallback, errorCallback) {
    if (errorCallback == null) { errorCallback = function() {}}
       if (typeof errorCallback != "function")  {
           console.log("error callback parameter must be a function");
           return
       }
       if (typeof successCallback != "function") {
           console.log("success callback parameter must be a function");
           return
       }
       
    cordova.exec(
               successCallback, // success callback function
               errorCallback, // error callback function
               'IMSetupStub', // mapped to our native Java class name
               'show',   // with this action name
               [{                
//                   "url": url,
               }]
       ); 
};


//-------------------------------------------------------------------
if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.Settings) {
    window.plugins.Settings = new Settings();
}