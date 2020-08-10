import { TechnicalOpinionService } from "./../../../_services/technicalopinion.service";
import { Component, OnInit } from "@angular/core";
import {
  AlertController,
  LoadingController,
  ToastController,
} from "@ionic/angular";
import { AuthenticationService } from "@app/_services";
import { Process, TechnicalOpinion } from "@app/_models";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { ProcessService } from "@app/_services/process.service";

@Component({
  selector: "app-technical-list",
  templateUrl: "./technical-list.component.html",
})
export class TechnicalListComponent implements OnInit {
  submitted = false;
  processTechnicalOpinionForm: FormGroup;
  technicalOpinionsActualUser: TechnicalOpinion[] = [];
  editForm: FormGroup;

  constructor(
    public fb: FormBuilder,
    private actRoute: ActivatedRoute,
    private technicalOpinionService: TechnicalOpinionService,
    private auth: AuthenticationService,
    private toast: ToastController,
    private loading: LoadingController
  ) {}

  async ngOnInit() {
    await this.fetchTechnicalOpinions();
    this.editForm = this.fb.group({
      technicalOpinion: ["", [Validators.required]],
    });
  }

  async fetchTechnicalOpinions() {
    let res = await this.technicalOpinionService
      .getByUser(this.auth.currentUserValue)
      .toPromise();
    this.technicalOpinionsActualUser = res;
  }

  async saveTechnicalOpinion(technicalOpinion: TechnicalOpinion) {
    if (technicalOpinion.technicalOpinion) {
      const loading = await this.loading.create({ spinner: "circles" });
      await loading.present();
      let newTechnicalOpinion: TechnicalOpinion = {
        process: technicalOpinion.process,
        user: technicalOpinion.user,
        id: technicalOpinion.id,
        hasTechnicalOpinionPending: false,
        technicalOpinion: technicalOpinion.technicalOpinion
          ? technicalOpinion.technicalOpinion
          : "",
      };
      await this.technicalOpinionService
        .updateTechnicalOpinion(newTechnicalOpinion)
        .toPromise();
      await this.fetchTechnicalOpinions();
      const toast = await this.toast.create({
        message: "Parecer técnico atualizado com sucesso.",
      });
      await loading.dismiss();
      await toast.present();
    } else {
      const toast = await this.toast.create({
        message: "O parecer técnico é obrigatório!",
      });
      await toast.present();
    }
  }
}
