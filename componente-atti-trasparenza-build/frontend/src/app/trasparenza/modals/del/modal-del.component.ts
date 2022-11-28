import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-del-bmodal',
  templateUrl: './modal-del.component.html',
  styleUrls: ['./modal-del.component.scss']
})
export class DettaglioDelBModalComponent implements OnInit, OnDestroy {
  @Input() content: any;

  constructor(public activeModal: NgbActiveModal,
  			  public translateService: TranslateService) {}

  ngOnInit() {
  }
  
  ngOnDestroy() {
  }
}
