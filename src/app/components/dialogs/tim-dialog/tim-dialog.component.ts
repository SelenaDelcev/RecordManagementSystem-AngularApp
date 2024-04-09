import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Tim } from 'src/app/models/tim';
import { Liga } from 'src/app/models/liga';
import { LigaService } from 'src/app/services/liga.service';
import { TimService } from 'src/app/services/tim.service';

@Component({
  selector: 'app-tim-dialog',
  templateUrl: './tim-dialog.component.html',
  styleUrls: ['./tim-dialog.component.css']
})
export class TimDialogComponent implements OnInit {

  flag!:number;
  subscription!: Subscription;
  lige!: Liga[];

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<TimDialogComponent>,
    @Inject (MAT_DIALOG_DATA) public data: Tim,
    public ligaService: LigaService,public timService: TimService) { }

  ngOnInit(): void {
    this.ligaService.getAllLigas().subscribe(ligeIzBaze => {
      this.lige = ligeIzBaze;
    })
  }

  compareTo(a:any, b:any) {
    return a.id == b.id;
  }

  public add():void {
    this.timService.addTim(this.data).subscribe(() => {
      this.snackBar.open('Uspešno dodat tim ' + this.data.naziv, 'OK', {duration:2500})
    },
    (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom dodavanja tima', 'Zatvori', {duration:2500})
    });
  }

  public update():void {
    this.timService.updateTim(this.data).subscribe(() => {
      this.snackBar.open('Uspešno modifikovan tim ' + this.data.naziv, 'OK', {duration:2500})
    },
    (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom dodavanja tima', 'Zatvori', {duration:2500})
    });
  }

  public delete():void {
    this.timService.deleteTim(this.data.id).subscribe(() => {
      this.snackBar.open('Uspešno obrisan tim ' + this.data.naziv, 'OK', {duration:2500})
    },
    (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom brisanja tima', 'Zatvori', {duration:2500})
    });
  }

  public cancel():void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste.', 'Zatvori', {duration:1000});
  }

}
