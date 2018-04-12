<%@include file="/WEB-INF/views/header.jsp" %>


<style>
    .username.ng-valid {
        background-color: lightgreen;
    }

    .username.ng-dirty.ng-invalid-required {
        background-color: red;
    }

    .username.ng-dirty.ng-invalid-minlength {
        background-color: yellow;
    }

    .email.ng-valid {
        background-color: lightgreen;
    }

    .email.ng-dirty.ng-invalid-required {
        background-color: red;
    }

    .email.ng-dirty.ng-invalid-email {
        background-color: yellow;
    }

</style>


<body ng-app="myApp" class="ng-cloak">
<div class="generic-container" ng-controller="LessonTimeController as ctrl">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">LessonTime edit.</span></div>
        <div class="formcontainer" style="padding: 20px;">
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.lessonTime.lessonId"/>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">number</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.lessonTime.number" name="number"
                                   class="username form-control input-sm" placeholder="Enter number" required
                                   ng-minlength="1"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.number.$error.required">This is a required field</span>
                                <span ng-show="myForm.number.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">startTime</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.lessonTime.startTime" name="startTime" class="form-control input-sm"
                                   placeholder="Enter startTime" required ng-minlength="8" ng-maxlength="8"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.startTime.$error.required">This is a required field</span>
                                <span ng-show="myForm.startTime.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">endTime</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.lessonTime.endTime" name="endTime"
                                   class="text form-control input-sm" placeholder="Enter your entTime" required
                                   ng-minlength="8" ng-maxlength="8"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.endTime.$error.required">This is a required field</span>
                                <span ng-show="myForm.endTime.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight" style="padding-left: 96px;">
                        <input type="submit" value="{{!ctrl.lessonTime.lessonId ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm"
                               ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm"
                                ng-disabled="myForm.$pristine">Reset Form
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of lessonTimes </span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>lessonId.</th>
                    <th>number</th>
                    <th>startTime</th>
                    <th>endTime</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="l in ctrl.lessonTimes">
                    <td><span ng-bind="l.lessonId"></span></td>
                    <td><span ng-bind="l.number"></span></td>
                    <td><span ng-bind="l.startTime"></span></td>
                    <td><span ng-bind="l.endTime"></span></td>
                    <td>
                        <button type="button" ng-click="ctrl.edit(l.lessonId)" class="btn btn-success custom-width" style="padding-top: 7px;">Edit
                        </button>
                        <button type="button" ng-click="ctrl.remove(l.lessonId)" class="btn btn-danger custom-width" style="padding-top: 7px;">Remove
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="<c:url value='/resources/js/app.js' />"></script>
<script src="<c:url value='/resources/js/service/service.js' />"></script>
<script src="<c:url value='/resources/js/controller/lessonTime_controller.js' />"></script>

<%@include file="/WEB-INF/views/footer.jsp" %>