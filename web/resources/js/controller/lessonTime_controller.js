'use strict';

angular.module('myApp').controller('LessonTimeController', ['$scope', 'Service', function($scope, Service) {
    var self = this;
    self.lessonTime={lessonId:null,number:null,startTime:null,endTime:null};
    self.lessonTimes=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAll();

    function fetchAll(){
        Service.fetchAll()
            .then(
            function(d) {
                self.lessonTimes = d;
            },
            function(errResponse){
                console.error('Error while fetching.');
            }
        );
    }

    function create(lessonTime){
        Service.create(lessonTime)
            .then(
            fetchAll,
            function(errResponse){
                console.error('Error while creating.');
            }
        );
    }

    function update(lessonTime, id){
        Service.update(lessonTime, id)
            .then(
            fetchAll,
            function(errResponse){
                console.error('Error while updating.');
            }
        );
    }

    function removeID(id){
        Service.remove(id)
            .then(
            fetchAll,
            function(errResponse){
                console.error('Error while deleting.');
            }
        );
    }

    function submit() {
        if(self.lessonTime.lessonId===null){
            create(self.lessonTime);
        }else{
            update(self.lessonTime, self.lessonTime.lessonId);
        }
        reset();
    }

    function edit(id){
        for(var i = 0; i < self.lessonTimes.length; i++){
            if(self.lessonTimes[i].lessonId === id) {
                self.lessonTime = angular.copy(self.lessonTimes[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.lessonTime.lessonId === id) {
            reset();
        }
        removeID(id);
    }


    function reset(){
        self.lessonTime={lessonId:null,number:null,startTime:null,endTime:null};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
