<div id="modalDeleteUser">
    <div class="modal-header">
        <button type="button" class="close" ng-click="cancel()">&times;</button>
        <h4 class="modal-title">{{title}}</h4>
    </div>
    <div class="modal-body">
        <p>{{'LST_DELETE_CONTENT' | translate}}</p>
    </div>
    <div class="modal-footer">
        <div class="row">
            <div class="col-md-6 col-xs-6">
                <button id="btnModalDelete" class="btn btn-success btn-block" ng-click="comfirm()">
                    <i class="glyphicon glyphicon-ok icon-modal-delete"></i><span>{{'LST_DELETE' | translate}}</span>
                </button>
            </div>
            <div class="col-md-6 col-xs-6">
                <button id="btnModalCancel" class="btn btn-danger btn-block" ng-click="cancel()">
                    <i class="glyphicon glyphicon-remove icon-modal-delete"></i><span>{{'LST_BTN_CANCEL' |
                        translate}}</span>
                </button>
            </div>
        </div>
    </div>
</div>