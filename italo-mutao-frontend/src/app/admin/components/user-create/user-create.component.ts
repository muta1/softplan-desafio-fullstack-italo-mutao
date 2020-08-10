import { Component, OnInit, NgZone } from "@angular/core";
import { User, Role, RoleTranslator } from "@app/_models";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { UserService } from "@app/_services";
import { first } from "rxjs/operators";

@Component({
  selector: "app-user-create",
  templateUrl: "./user-create.component.html",
})
export class UserCreateComponent implements OnInit {
  public loading = false;
  public users: User[] = [];

  public submitted = false;
  public userForm: FormGroup;
  public roles: Role[] = [Role.Admin, Role.Screening, Role.Finisher];

  constructor(
    public fb: FormBuilder,
    private router: Router,
    private ngZone: NgZone,
    private userService: UserService
  ) {
    this.mainForm();
  }

  translator(role: string): string {
    return RoleTranslator.translate(role);
  }

  ngOnInit() {
    this.loading = true;
    this.userService
      .getUsers()
      .pipe(first())
      .subscribe((api) => {
        this.loading = false;
        this.users = api;
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
          this.ngZone.run(() => this.router.navigateByUrl("/user-list"));
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }
}
