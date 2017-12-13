/*var CUSRapp = angular.module('CUSRapp', ['ui.router', 'ngStorage']);
CUSRapp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
//States start here-----------------------------------------------------------------
    .state('app',{
    	url: '/',
    	views: {
    		'header@':{
    			templateUrl: 'resources/templates/header.html'
    		},
    		'content@':{
    			templateUrl: 'resources/templates/index.html',
    			controller: 'registercontroller'
    		}
    	}
    })
    
    
       
        
//-------------------------------------------------------------------------------       
        .state('app.login', {
            url: 'login',
            views: {
                'header':{
                    templateUrl: 'resources/templates/header.html'
                },
                'content':{
                	templateUrl: 'resources/templates/index.html',
                	controller: 'logincontroller'
                }

            }
        })
  //--------------------------------------------------------------------------      
        
        .state('app.UserHome',{
        	url: 'mainTest',
        	views: {
        		'header@':{
        			templateUrl: 'resources/templates/header.html'
        		},
        		'content@':{
        			templateUrl: 'resources/templates/UserHome.html',
        			controller: 'homecontroller'
        		}
        	}
        })
        
});
 //--------------------------------------------------------------------------------       

CUSRapp.controller('registercontroller',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
	$scope.Register=function(){
	       
	       console.log("inside register function");
		  
	  		   $http({
		           method:'post',
		           url:'/register',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		               email:$scope.email,
		               password:$scope.password
		           }
		
		       }).then(function(data){
		           console.log(data);
		           console.log("inside success");
		           console.log($scope.email);
		           $state.transitionTo("app.login");
		    	   if(data.status==200){
		               console.log(data.token);
		    		  
		            }
		           else{
		               
		
		           }
		       })
	       }
	
	
	
}]);
 

CUSRapp.controller('logincontroller',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
	$scope.Login = function(){
		 console.log("inside register function");
		  
		   $http({
	           method:'post',
	           url:'/login',
	           headers: {"Content-Type":"application/x-www-form-urlencoded"},
	           transformRequest: function(obj) {
	               var str = [];
	               for(var p in obj)
	               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	               return str.join("&");
	           },
	           data:{
	               email:$scope.email,
	               password:$scope.password
	           }
	
	       }).then(function(data){
	           console.log(data);
	           console.log("inside success");
	           console.log($scope.email);
	    	   if(data.status==200){
	               console.log(data.token);
	    		   $state.transitionTo("app.UserHome");
	            }
	           else{
	               
	
	           }
	       })
     }
}]);

*/

var CUSRapp = angular.module('CUSRapp', ['ui.router']);
CUSRapp.config(function($stateProvider){
	
	$stateProvider.state('app',{
	    	url: '/',
	    	views: {
	    		'header@':{
	    			templateUrl: 'resources/templates/header.html'
	    		},
	    		'content@':{
	    			templateUrl: 'resources/templates/index.html',
	    			controller: 'registercontroller'
	    		}
	    	}
	    })
	  /*  
	    $stateProvider.state('app.login',{
	    	url: 'login',
	    	views: {
	    		'header@':{
	    			templateUrl: 'resources/templates/header.html'
	    		},
	    		'content@':{
	    			templateUrl: 'resources/templates/login.html',
	    			controller: 'logincontroller'
	    		}
	    	}
	    })
	    */
});
CUSRapp.controller('registercontroller', function($scope, $http, $state, $location) {
	$scope.Register=function(){
	       
	       console.log("inside register function");
		  
	  		   $http({
		           method:'post',
		           url:'/register',
		           headers: {"Content-Type":"application/x-www-form-urlencoded"},
		           transformRequest: function(obj) {
		               var str = [];
		               for(var p in obj)
		               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		               return str.join("&");
		           },
		           data:{
		               email:$scope.email,
		               password:$scope.password
		           }
		
		       }).then(function(data){
		           console.log(data);
		           console.log("inside success");
		           console.log($scope.email);
		       //   $state.transitionTo("app.login");
		          console.log("After State Transition");
		    	   if(data.status==200){
		               console.log(data.token);
		    		  
		            }
		           else{
		               
		
		           }
		       })
	       }
})
 
CUSRapp.controller('logincontroller', function($scope, $http,$state, $location) {
	$scope.Login = function(){
		 console.log("inside login function");
		  
		   $http({
	           method:'post',
	           url:'/login',
	           headers: {"Content-Type":"application/x-www-form-urlencoded"},
	           transformRequest: function(obj) {
	               var str = [];
	               for(var p in obj)
	               str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	               return str.join("&");
	           },
	           data:{
	               email:$scope.email,
	               password:$scope.password
	           }
	
	       }).then(function(data){
	           console.log(data);
	           console.log("inside success");
	           console.log($scope.email);
	    	   if(data.status==200){
	               console.log(data.token);
	    		 //  $state.transitionTo("app.UserHome");
	            }
	           else{
	               
	
	           }
	       })
    }
})