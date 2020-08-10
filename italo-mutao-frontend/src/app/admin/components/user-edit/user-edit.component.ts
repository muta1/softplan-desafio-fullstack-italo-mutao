import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { User, Role, RoleTranslator } from "@app/_models";
import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "@app/_services";
import { ToastController } from "@ionic/angular";

@Component({
  selector: "app-user-edit",
  templateUrl: "./user-edit.component.html",
})
export class UserEditComponent implements OnInit {
  submitted = false;
  editForm: FormGroup;

  public roles: Role[] = [Role.Admin, Role.Screening, Role.Finisher];

  constructor(
    public fb: FormBuilder,
    private actRoute: ActivatedRoute,
    private apiService: UserService,
    private router: Router,
    private toast: ToastController
  ) {}

  ngOnInit() {
    this.updateUser();
    let id = this.actRoute.snapshot.paramMap.get("id");
    this.getUser(id);
    this.editForm = this.fb.group({
      name: ["", [Validators.required]],
      password: ["", [Validators.required]],
      role: ["", [Validators.required]],
    });
  }

  translator(role: string): string {
    return RoleTranslator.translate(role);
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

  getUser(id) {
    this.apiService.getUser(id).subscribe((data) => {
      console.log("Edit: ", data);
      this.editForm.setValue({
        name: data["name"],
        password: data["password"],
        role: data["role"],
      });
    });
  }

  updateUser() {
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
      let id = this.actRoute.snapshot.paramMap.get("id");
      this.editForm.value.id = id;
      this.apiService.updateUser(this.editForm.value).subscribe(
        async (res) => {
          console.log("res ===> ", res);
          this.router.navigateByUrl("/user-list");
          let toast = await this.toast.create({
            message: "Usuário atualizado com sucesso!",
            duration: 3000,
          });
          await toast.present();
          console.log("Content updated successfully!");
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }
}
