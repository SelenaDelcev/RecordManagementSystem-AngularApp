import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Igrac } from 'src/app/models/igrac';
import { Nacionalnost } from 'src/app/models/nacionalnost';
import { IgracService } from 'src/app/services/igrac.service';
import { NacionalnostService } from 'src/app/services/nacionalnost.service';

@Component({
  selector: 'app-igrac-dialog',
  templateUrl: './igrac-dialog.component.html',
  styleUrls: ['./igrac-dialog.component.css']
})
export class IgracDialogComponent implements OnInit, OnDestroy {

  flag!:number;
  nacionalnosti!: Nacionalnost[];
  subscription!: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<IgracDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Igrac,
    private igracService: IgracService,
    private nacionalnostService: NacionalnostService) { }

  ngOnInit(): void {
    this.subscription = this.nacionalnostService.getAllNacionalnosts().subscribe(data => {
      this.nacionalnosti = data;
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  compareTo(a: any, b:any) {
    return a.id == b.id;
  }

  public add():void {
    this.igracService.addIgrac(this.data).subscribe(() => {
      this.snackBar.open('Uspešno dodat igrač ', 'OK', {duration:2500})
    },
    (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom dodavanja igrača', 'Zatvori', {duration:2500})
    });
  }

  public update():void {
    this.igracService.updateIgrac(this.data).subscribe(() => {
      this.snackBar.open('Uspešno modifikovan igrač ', 'OK', {duration:2500})
    },
    (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom dodavanja igrača', 'Zatvori', {duration:2500})
    });
  }

  public delete():void {
    this.igracService.deleteIgrac(this.data.id).subscribe(() => {
      this.snackBar.open('Uspešno obrisan igrač ', 'OK', {duration:2500})
    },
    (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom brisanja igrača', 'Zatvori', {duration:2500})
    });
  }

  public cancel() {
    this.dialogRef.close(); 
    this.snackBar.open('Odustali ste', 'Zatvori', {duration:1000});
  }

}
