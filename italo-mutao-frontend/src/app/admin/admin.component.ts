import { Component, OnInit, NgZone } from "@angular/core";
import { first } from "rxjs/operators";

import { User, Role } from "@app/_models";
import { UserService } from "@app/_services";
import { FormBuilder, Validators, FormGroup } from "@angular/forms";
import { Router } from "@angular/router";

@Component({ templateUrl: "admin.component.html" })
export class AdminComponent implements OnInit {
  loading = false;
  users: User[] = [];

  submitted = false;
  userForm: FormGroup;
  roles: Role[] = [Role.Admin, Role.Screening, Role.Finisher];

  constructor(
    public fb: FormBuilder,
    private router: Router,
    private ngZone: NgZone,
    private userService: UserService
  ) {
    this.mainForm();
  }

  ngOnInit() {
    this.loading = true;
    this.userService
      .getUsers()
      .pipe(first())
      .subscribe((api) => {
        this.loading = false;
        this.users = api.response;
      });
  }

  mainForm() {
    this.userForm = this.fb.group({
      name: ["", [Validators.required]],
      password: ["", [Validators.required]],
      role: ["", [Validators.required]],
    });
  }

  // Choose designation with select dropdown
  updateProfile(e) {
    this.userForm.get("role").setValue(e, {
      onlySelf: true,
    });
  }

  // Getter to access form control
  get myForm() {
    return this.userForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (!this.userForm.valid) {
      return false;
    } else {
      console.log("userForm: ", this.userForm.value);

      this.userService.createUser(this.userForm.value).subscribe(
        (res) => {
          console.log("User successfully created!");
          this.ngZone.run(() => this.router.navigateByUrl("/employees-list"));
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }
}
