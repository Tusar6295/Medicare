import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { LoginService } from 'src/app/services/login.service';
import { AppearanceAnimation, ConfirmBoxEvokeService, ConfirmBoxInitializer, DialogLayoutDisplay, DisappearanceAnimation } from '@costlydeveloper/ngx-awesome-popup';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  newConfirmBox = new ConfirmBoxInitializer();

  constructor(public loginService: LoginService,
    private toast: NgToastService,
    private confirmLogout: ConfirmBoxEvokeService){
     

    this.newConfirmBox.setTitle('Logout');
    this.newConfirmBox.setMessage('Are you sure you want to logout?');


    this.newConfirmBox.setConfig({
    layoutType: DialogLayoutDisplay.WARNING, 
    animationIn: AppearanceAnimation.BOUNCE_IN, 
    animationOut: DisappearanceAnimation.BOUNCE_OUT, 
    buttonPosition: 'center',
    height: '200px', 
    width: '400px', 
    });

    this.newConfirmBox.setButtonLabels('Yes', 'No');
  }

  currentUser: any = localStorage?.getItem('username');

  confirm(){
    this.newConfirmBox.openConfirmBox$().subscribe(resp => {
      if(resp.clickedButtonID == "yes"){
      this.logout();
      }
    });
  }

  logout(){
    this.loginService.logout();
    setTimeout(() => {
      this.toast.success({detail:"Log out successful", summary:'success', duration:2000});
  }, 500);
  }
}
