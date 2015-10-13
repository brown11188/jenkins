function LoginPage() {
    this.open = function() {
        browser.get('brea');
    };
    // Get element login page.
    this.getLoginUserNameField = function() {
        return element(by.model('username'));
    };
    this.getLoginPasswordField = function() {
        return element(by.model('password'));
    };
    this.getLoginButton = function() {
        return element(by.name('submit'));
    };
}
function HomePage() {
    this.getAdminButton = function() {
        return element(by.css('a[href="admin"]'));
    };
};
function AdminPage() {
    var repeater = 'user in filteredUser =  (filteredListUser |' + ' orderBy:sort.column:sort.descending |'
            + ' filter:search) track by $index ';
    // Get element admin page.
    this.getSearchField = function() {
        return element(by.model('search'));
    };
    this.clearField = function(field) {
        field.sendKeys(protractor.Key.chord(protractor.Key.CONTROL, 'a'));
        field.sendKeys(protractor.Key.BACK_SPACE);
        field.clear();
    };
    this.getListUser = function() {
        return element.all(by.repeater(repeater));
    };
    this.getSelectEntries = function() {
        return element.all(by.options('item for item in itemsPerPage'));
    };
    var getButtonInListById = function(param) {
        var list = element.all(by.repeater(repeater));
        return list.last().element(by.id(param));
    };
    this.getButtonEditUser = function() {
        return getButtonInListById('lstEditUser');
    };
    this.getButtonDeleteUser = function() {
        return getButtonInListById('lstDeleteUser');
    };
    this.getAlertSuccess = function() {
        return element(by.css('.alert.alert-success'));
    };
    this.getButtonSave = function() {
        return element(by.css('.btn.btn-success'));
    };
    this.getButtonCancel = function() {
        return element(by.css('.btn.btn-danger'));
    };
    this.getButtonCloseAlert = function() {
        return element(by.css('.close'));
    };
    this.getElementById = function(param) {
        return element(by.id(param));
    };
    // Add user view
    this.getMenuAddUser = function() {
        return element(by.css('a[href="#/adduser"]'));
    };
    this.getRandomString = function() {
        var strValues = "abcdefghijk123456789";
        var str = "";
        var length = strValues.length;
        for (var i = 0; i < 7; i++) {
            str = str + strValues.charAt(Math.round(length * Math.random()));
        }
        return str;
    };
    this.getElementByModel = function(param) {
        return element(by.model(param));
    };
    this.getSelectRoles = function() {
        return element.all(by.options('role.id as role.description for role in roles'));
    };
    this.getErrorAlert = function() {
        return element(by.css('.form-group.alert.alert-danger'));
    };
    this.getPaginationLast = function() {
        return element(by.css('.pagination-last.ng-scope')).element(by.css('.ng-binding'));
    };
    this.getLastUserToEdit = function() {
        this.getPaginationLast().click();
        this.getButtonEditUser().click();
    };
    this.checkListUser = function() {
        expect(browser.getCurrentUrl()).toContain('/brea/admin#/users');
    };
    this.checkCancelButton = function() {
        this.getButtonCancel().click();
        this.checkListUser();
    };
    this.checkSaveButton = function() {
        this.getButtonSave().click();
        this.checkListUser();
    };
};
describe('Admin Page Testing', function() {
    var adminPage;
    beforeEach(function() {
        adminPage = new AdminPage();
    });
    it('Login', function() {
        var loginPage = new LoginPage();
        loginPage.open();
        expect(browser.getCurrentUrl()).toContain('/brea/login');
        loginPage.getLoginUserNameField().sendKeys('admin');
        loginPage.getLoginPasswordField().sendKeys('admin');
        loginPage.getLoginButton().click();

    });
    it('Home Page', function() {
        var homePage = new HomePage();
        expect(browser.getCurrentUrl()).toContain('/brea/home');
        homePage.getAdminButton().click();

    });
    it('List User', function() {
        adminPage.checkListUser();

        // Show entries
        var selectEntries = adminPage.getSelectEntries();
        var counter = selectEntries.count();
        counter.then(function(value) {
            var option;
            for (var i = 0; i < value; i++) {
                option = selectEntries.get(i);
                option.click();
                option.getText().then(function(value) {
                    expect(adminPage.getListUser().count()).toBeLessThan(parseInt(value) + 1);
                });
            }
        });
        adminPage.getSearchField().sendKeys('~!@#$%^&*()_+');
        expect(adminPage.getListUser().count()).toEqual(0);
        adminPage.clearField(adminPage.getSearchField());
        expect(adminPage.getListUser().count()).toBeGreaterThan(0);
    });
    it('Adding User', function() {
        // Add user
        adminPage.getMenuAddUser().click();
        expect(browser.getCurrentUrl()).toContain('/brea/admin#/adduser');
        adminPage.checkCancelButton();
        expect(adminPage.getAlertSuccess().isPresent()).toBeFalsy();
        adminPage.getMenuAddUser().click();
        var randomStr = adminPage.getRandomString();
        // Check user name already exists
        var userNameField = adminPage.getElementByModel('user.username');
        userNameField.sendKeys('admin');
        adminPage.getElementByModel('user.usersInfo.userFirstName').sendKeys(randomStr);
        adminPage.getElementByModel('user.usersInfo.userLastName').sendKeys(randomStr);
        adminPage.getElementByModel('user.usersInfo.userEmail').sendKeys(randomStr + '@gmail.com');
        adminPage.getElementByModel('user.usersInfo.userPhone').sendKeys('123-456-7899');
        adminPage.getElementByModel('user.usersInfo.userAddress').sendKeys(randomStr);
        var selectRoles = adminPage.getSelectRoles();
        selectRoles.last().click();
        adminPage.getElementByModel('user.password').sendKeys('123');
        adminPage.getElementByModel('verifyNewPassword').sendKeys('123');
        adminPage.getButtonSave().click();
        // Should show error.
        expect(adminPage.getErrorAlert().isDisplayed()).toBeTruthy();
        adminPage.clearField(userNameField);
        userNameField.sendKeys(randomStr);
        // Click save button
        adminPage.checkSaveButton();
        expect(adminPage.getAlertSuccess().isDisplayed()).toBe(true);
        adminPage.getButtonCloseAlert().click();
        expect(adminPage.getAlertSuccess().isPresent()).toBeFalsy();
    });
    it('Editing User', function() {
        adminPage.getListUser().count().then(function(value) {
            if (value > 0) {
                adminPage.getLastUserToEdit();
                expect(browser.getCurrentUrl()).toContain('/brea/admin#/user/');
                adminPage.checkCancelButton();
                expect(adminPage.getAlertSuccess().isPresent()).toBeFalsy();
                adminPage.getLastUserToEdit();
                adminPage.checkSaveButton();
                expect(adminPage.getAlertSuccess().isDisplayed()).toBe(true);
                adminPage.getButtonCloseAlert().click();
            }
        });
    });
    it('Deleting User', function() {
        adminPage.getListUser().count().then(function(value) {
            if (value > 0) {
                adminPage.getPaginationLast().click();
                adminPage.getButtonDeleteUser('lstDeleteUser').click();
                expect(adminPage.getElementById('modalDeleteUser').isDisplayed()).toBe(true);
                adminPage.getElementById('btnModalCancel').click();
                expect(adminPage.getElementById('modalDeleteUser').isPresent()).toBeFalsy();
                adminPage.getButtonDeleteUser('lstDeleteUser').click();
                adminPage.getElementById('btnModalDelete').click();
                expect(adminPage.getAlertSuccess().isDisplayed()).toBe(true);
                adminPage.getButtonCloseAlert().click();
                expect(adminPage.getAlertSuccess().isPresent()).toBeFalsy();
            }
        });
    });
    it('Admin Profile', function() {
        adminPage.getElementById('adminProfile').click();
        expect(browser.getCurrentUrl()).toContain('/brea/admin#/profile/');
        adminPage.checkCancelButton();
        adminPage.getElementById('adminProfile').click();
        adminPage.checkSaveButton();
        expect(adminPage.getAlertSuccess().isPresent()).toBeFalsy();
    });
    it('Change Password', function() {
        adminPage.getElementById('adminPassword').click();
        expect(browser.getCurrentUrl()).toContain('/brea/admin#/password');
        adminPage.checkCancelButton();
        adminPage.getElementById('adminPassword').click();
        adminPage.getElementByModel('user.currentPass').sendKeys('!@#!@#$%^@');
        adminPage.getElementByModel('user.newPass').sendKeys('admin');
        adminPage.getElementByModel('verifyNewPassword').sendKeys('admin');
        adminPage.getButtonSave().click();
        expect(adminPage.getErrorAlert().isDisplayed()).toBeTruthy();
        adminPage.clearField(adminPage.getElementByModel('user.currentPass'));
        adminPage.getElementByModel('user.currentPass').sendKeys('admin');
        adminPage.checkSaveButton();
    });
});