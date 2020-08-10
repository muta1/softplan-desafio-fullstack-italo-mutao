import { Component, OnInit, NgZone } from "@angular/core";
import { Process } from "@app/_models";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { ProcessService } from "@app/_services/process.service";
import { first } from "rxjs/operators";

@Component({
  selector: "app-process-create",
  templateUrl: "./process-create.component.html",
})
export class ProcessCreateComponent implements OnInit {
  public loading = false;
  public processes: Process[] = [];

  public submitted = false;
  public processForm: FormGroup;

  constructor(
    public fb: FormBuilder,
    private router: Router,
    private ngZone: NgZone,
    private processService: ProcessService
  ) {
    this.mainForm();
  }

  ngOnInit() {
    this.loading = true;
    this.processService
      .getProcesses()
      .pipe(first())
      .subscribe((api) => {
        this.loading = false;
        this.processes = api.response;
      });
  }

  mainForm() {
    this.processForm = this.fb.group({
      name: ["", [Validators.required]],
    });
  }

  // Getter to access form control
  get myForm() {
    return this.processForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (!this.processForm.valid) {
      return false;
    } else {
      console.log("processForm: ", this.processForm.value);

      this.processService.createProcess(this.processForm.value).subscribe(
        (res) => {
          console.log("Process successfully created!");
          this.ngZone.run(() => this.router.navigateByUrl("/process-list"));
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }
}
