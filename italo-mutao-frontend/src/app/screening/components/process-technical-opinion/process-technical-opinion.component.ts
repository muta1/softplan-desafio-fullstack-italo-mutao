import { TechnicalOpinion } from "./../../../_models/technicalopinion";
import { TechnicalOpinionService } from "./../../../_services/technicalopinion.service";
import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder } from "@angular/forms";
import { Process, User } from "@app/_models";
import { ActivatedRoute, Router } from "@angular/router";
import { ProcessService } from "@app/_services/process.service";
import { ToastController, LoadingController } from "@ionic/angular";
import { UserService } from "@app/_services";

@Component({
  selector: "app-process-technical-opinion",
  templateUrl: "./process-technical-opinion.component.html",
})
export class ProcessTechnicalOpinionComponent implements OnInit {
  submitted = false;
  processTechnicalOpinionForm: FormGroup;
  process: Process;
  finalizerUsers: User[] = [];
  technicalOpinions: TechnicalOpinion[] = [];

  constructor(
    public fb: FormBuilder,
    private actRoute: ActivatedRoute,
    private processService: ProcessService,
    private technicalOpinionService: TechnicalOpinionService,
    private userService: UserService,
    private router: Router,
    private toast: ToastController,
    private loading: LoadingController
  ) {}

  async ngOnInit() {
    let id = this.actRoute.snapshot.paramMap.get("id");
    await this.getProcessAndTechnicalOpinionUsers(id);
  }

  async addToActualProcess(user: User, index) {
    const loading = await this.loading.create({ spinner: "circles" });
    await loading.present();
    this.finalizerUsers.splice(index, 1);
    let technicalOpinion: TechnicalOpinion = {
      process: this.process,
      user: user,
      hasTechnicalOpinionPending: true,
    };

    let inserted = await this.technicalOpinionService
      .createTechnicalOpinion(technicalOpinion)
      .toPromise();

    this.technicalOpinions.push(inserted.response);
    await loading.dismiss();
  }

  async getProcessAndTechnicalOpinionUsers(id) {
    const loading = await this.loading.create({ spinner: "circles" });
    await loading.present();
    this.process = await this.processService.getProcess(id).toPromise();
    this.technicalOpinions = await this.technicalOpinionService
      .getByProccess(this.process)
      .toPromise();
    this.finalizerUsers = await this.userService
      .getOtherFinisherUsers(this.technicalOpinions)
      .toPromise();

    await loading.dismiss();
  }

  onSubmit() {}
}
