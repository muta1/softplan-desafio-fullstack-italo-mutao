import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { User, Role } from "@app/_models";
import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "@app/_services";

@Component({
  selector: "app-user-edit",
  templateUrl: "./user-edit.component.html",
})
export class UserEditComponent implements OnInit {
  submitted = false;
  editForm: FormGroup;
  employeeData: User[];
  public roles: Role[] = [Role.Admin, Role.Screening, Role.Finisher];

  constructor(
    public fb: FormBuilder,
    private actRoute: ActivatedRoute,
    private apiService: UserService,
    private router: Router
  ) {}

  ngOnInit() {
    this.updateEmployee();
    let id = this.actRoute.snapshot.paramMap.get("id");
    this.getEmployee(id);
    this.editForm = this.fb.group({
      name: ["", [Validators.required]],
      password: ["", [Validators.required]],
      role: ["", [Validators.required]],
    });
  }

  // Choose options with select-dropdown
  updateProfile(e) {
    this.editForm.get("role").setValue(e, {
      onlySelf: true,
    });
  }

  // Getter to access form control
  get myForm() {
    return this.editForm.controls;
  }

  getEmployee(id) {
    this.apiService.getUser(id).subscribe((data) => {
      console.log("Edit: ", data);
      this.editForm.setValue({
        name: data["name"],
        password: data["password"],
        role: data["role"],
      });
    });
  }

  updateEmployee() {
    this.editForm = this.fb.group({
      name: ["", [Validators.required]],
      password: ["", [Validators.required]],
      role: ["", [Validators.required]],
    });
  }

  onSubmit() {
    this.submitted = true;
    if (!this.editForm.valid) {
      return false;
    } else {
      if (window.confirm("Are you sure?")) {
        let id = this.actRoute.snapshot.paramMap.get("id");
        this.apiService.updateUser(this.editForm.value).subscribe(
          (res) => {
            this.router.navigateByUrl("/user-list");
            console.log("Content updated successfully!");
          },
          (error) => {
            console.log(error);
          }
        );
      }
    }
  }
}
