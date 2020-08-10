import { Component, OnInit } from "@angular/core";
import { Process } from "@app/_models";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { ProcessService } from "@app/_services/process.service";
import { ToastController } from "@ionic/angular";

@Component({
  selector: "app-process-edit",
  templateUrl: "./process-edit.component.html",
})
export class ProcessEditComponent implements OnInit {
  submitted = false;
  editForm: FormGroup;
  employeeData: Process[];

  constructor(
    public fb: FormBuilder,
    private actRoute: ActivatedRoute,
    private apiService: ProcessService,
    private router: Router,
    private toast: ToastController
  ) {}

  ngOnInit() {
    this.updateProcess();
    let id = this.actRoute.snapshot.paramMap.get("id");
    this.getEmployee(id);
    this.editForm = this.fb.group({
      name: ["", [Validators.required]],
    });
  }

  // Getter to access form control
  get myForm() {
    return this.editForm.controls;
  }

  getEmployee(id) {
    this.apiService.getProcess(id).subscribe((data) => {
      console.log("Edit: ", data);
      this.editForm.setValue({
        name: data["name"],
      });
    });
  }

  updateProcess() {
    this.editForm = this.fb.group({
      name: ["", [Validators.required]],
    });
  }

  onSubmit() {
    this.submitted = true;
    if (!this.editForm.valid) {
      return false;
    } else {
      let id = this.actRoute.snapshot.paramMap.get("id");
      this.editForm.value.id = id;
      this.apiService.updateProcess(this.editForm.value).subscribe(
        async (res) => {
          console.log("res ===> ", res);
          this.router.navigateByUrl("/process-list");
          let toast = await this.toast.create({
            message: "Processo atualizado com sucesso!",
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
