'use strict';

angular.module('myApp').factory('Service', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/api/lessonTime/';

    return {
        fetchAll: fetchAll,
        create: create,
        update: update,
        remove: remove
    };

    function fetchAll() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching.');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function create(item) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, item)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating.');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function update(item, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, item)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating.');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function remove(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting.');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
