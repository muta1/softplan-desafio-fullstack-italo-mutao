import { Component, OnInit } from "@angular/core";
import { ProcessService } from "@app/_services/process.service";
import { AlertController, LoadingController } from "@ionic/angular";

@Component({
  selector: "app-process-list",
  templateUrl: "./process-list.component.html",
})
export class ProcessListComponent implements OnInit {
  processes: any = [];

  constructor(
    private apiService: ProcessService,
    private alert: AlertController,
    private loading: LoadingController
  ) {
    this.readProcesses();
  }

  ngOnInit() {}

  readProcesses() {
    this.apiService.getProcesses().subscribe((data) => {
      this.processes = data.response;
    });
  }

  async removeUser(process, index) {
    let alert = await this.alert.create({
      header: "Aviso",
      message: `Deseja realmente remover processo: ${process.name} ?`,
      buttons: [
        {
          text: "Sim",
          handler: async () => {
            const loading = await this.loading.create({ spinner: "circles" });
            await loading.present();
            this.apiService.deleteProcess(process.id).subscribe(
              async (_) => {
                await loading.dismiss();
                console.log("process deleted");
                console.log(process);
                this.processes.splice(index, 1);
              },
              async (_) => {
                await loading.dismiss();
              }
            );
          },
        },
        {
          text: "Não",
        },
      ],
    });
    await alert.present();
  }
}
