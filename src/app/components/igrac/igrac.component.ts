import { Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { Igrac } from 'src/app/models/igrac';
import { MatTableDataSource } from '@angular/material/table';
import { Tim } from 'src/app/models/tim';
import { IgracService } from 'src/app/services/igrac.service';
import { MatDialog } from '@angular/material/dialog';
import { Nacionalnost } from 'src/app/models/nacionalnost';
import { IgracDialogComponent } from '../dialogs/igrac-dialog/igrac-dialog.component';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-igrac',
  templateUrl: './igrac.component.html',
  styleUrls: ['./igrac.component.css']
})
export class IgracComponent implements OnInit, OnDestroy, OnChanges {

  displayedColumns = ['id', 'ime', 'prezime', 'brojReg', 'datumRodjenja', 'nacionalnost', 'tim', 'actions'];
  dataSource!: MatTableDataSource<Igrac>;
  subscription!: Subscription;
  @Input() selektovanTim!: Tim;

  @ViewChild(MatSort, {static: false}) sort!: MatSort;
  @ViewChild(MatPaginator, {static:false}) paginator!: MatPaginator;

  constructor(private igracService: IgracService,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnChanges(): void {
    if(this.selektovanTim) {
      this.loadData();
    } 
  }

  loadData() {
    this.subscription = this.igracService.getIgracZaTimID(this.selektovanTim.id).subscribe(data => {
      this.dataSource = new MatTableDataSource(data)
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    }, (error: Error) => {
      console.log(error.name + ' ' + error.message);
    });
  }

  openDialog(flag: number, id?:number, ime?:string, prezime?:string, brojReg?:string, datumRodjenja?:Date, nacionalnost?: Nacionalnost, tim?: Tim) {
    const dialogRef = this.dialog.open(IgracDialogComponent,
      {data:{id,ime,prezime,brojReg,datumRodjenja,nacionalnost,tim}});

    dialogRef.componentInstance.flag = flag;
    if(flag === 1) {
      dialogRef.componentInstance.data.tim = this.selektovanTim;
    }

    dialogRef.afterClosed().subscribe(res => {
      if(res === 1) {
        this.loadData();
      }
    });
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value;
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;
  }

}
