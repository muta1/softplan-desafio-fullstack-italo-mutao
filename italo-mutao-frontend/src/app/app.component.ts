import { Component } from "@angular/core";
import { Router } from "@angular/router";

import { AuthenticationService } from "./_services";
import { User, Role } from "./_models";

@Component({ selector: "app", templateUrl: "app.component.html" })
export class AppComponent {
  currentUser: User;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.currentUser.subscribe((x) => {
      this.currentUser = x;
      console.log("this.currentUser = ", this.currentUser);
    });
  }

  get isAdmin() {
    return this.currentUser && this.currentUser.role === Role.Admin;
  }

  get isScreening() {
    return this.currentUser && this.currentUser.role === Role.Screening;
  }

  get isFinisher() {
    return this.currentUser && this.currentUser.role === Role.Finisher;
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(["/login"]);
  }
}
