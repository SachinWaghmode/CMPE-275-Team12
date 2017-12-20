
var routerApp = angular.module('routerApp', ['ui.router', 'ngStorage']);
routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
    //States start here-----------------------------------------------------------------
        .state('app', {
            url: '/',
            views: {
                'header':{
                    templateUrl: '/html/header.html'
                },
                'content':{
                    templateUrl: '/html/register.html',
                    controller: 'registerController'
                }

            }
        })

        //-------------------------------------------------------------------------------       
        .state('app.login',{
            url: 'login',
            views: {
                'header@':{
                    templateUrl: '/html/header.html'
                },
                'content@':{
                    templateUrl: '/html/login.html',
                    controller: 'loginController'
                }
            }
        })

        .state('app.homepage',{
            url: 'homepage',
            views: {
                'header@':{
                    templateUrl: '/html/header.html'
                },
                'content@':{
                    templateUrl: '/html/homepage.html',
                    controller: 'homeController'
                }
            }
        })
        //-------------------------------------------------------------------------------
        .state('app.search',{
            url: 'home',
            views: {
                'header@':{
                    templateUrl: '/html/header.html'
                },
                'content@':{
                    templateUrl: '/html/SearchTrain.html',
                    controller: 'searchController'
                }
            }
        })
        

});

//States end here----------------------------------------------------------------------------------------------
///Controllers Start here--------------------------------------------------------------------------------------


routerApp.controller('loginController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
    $scope.login=function(){

            $http({
                method:'post',
                url:'http://localhost:8080/login',
                headers: {"Content-Type":"application/x-www-form-urlencoded"},
                transformRequest: function(obj) {
                    var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                data:{
                    "email":$scope.email,
                    "password":$scope.password,
                }

            }).then(function(data){
                console.log(data);
                if(data.status==200){
                    console.log(data.token);
                    $state.transitionTo("app.homepage");
                }
                else{


                }
            },
                function errorCallback(response) {
                    console.log(response);
                    //$window.location.reload();
                });




    }
    $scope.switchToRegister=function(){
        $state.transitionTo("app.home");
    }
}])


routerApp.controller('registerController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
    $scope.register=function(){

        console.log("inside register function");

            $http({
                method:'post',
                url:'http://localhost:8080/register',
                headers: {"Content-Type":"application/x-www-form-urlencoded"},
                transformRequest: function(obj) {
                    var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                data:{

                    email:$scope.email,

                    password:$scope.password,



                }

            }).then(function(data){
                console.log(data);
                console.log("inside success");
                console.log($scope.email);
                if(data.status==200){
                    console.log(data.token);
                    $state.transitionTo("app.login");
                }
                else{


                }
            })


    }
    $scope.switchToLogin=function(){
        $state.transitionTo("app.login");
    }
}])

routerApp.controller('homeController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){

    $scope.search=function(){
        $state.transitionTo("app.search");
    }

    $scope.cancelTicket=function(){
        $state.transitionTo("app.search");
    }
    $scope.cancelTrain=function(){
        $state.transitionTo("app.search");
    }
}])



routerApp.controller('searchController',['$scope','$http','$state','$window',function($scope,$http,$state,$window){
    $scope.search=function(){

        console.log("inside register function");

        $http({
            method:'post',
            url:'http://localhost:8080/train/search',
            headers: {"Content-Type":"application/x-www-form-urlencoded"},
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            },
            data:{

                email:$scope.email,

                password:$scope.password,



            }

        }).then(function(data){
            console.log(data);
            console.log("inside success");
            console.log($scope.email);
            if(data.status==200){
                console.log(data.token);
                $state.transitionTo("app.login");
            }
            else{


            }
        })


    }
    $scope.switchToLogin=function(){
        $state.transitionTo("app.login");
    }
}])

//Controllers end here---------------------------------------------------------------------------------------------------
