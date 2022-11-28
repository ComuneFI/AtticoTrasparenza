import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-pd-bmodal',
  templateUrl: './modal-pd.component.html',
  styleUrls: ['./modal-pd.component.scss']
})
export class DettaglioPdBModalComponent implements OnInit, OnDestroy {
  @Input() content: any;

  constructor(public activeModal: NgbActiveModal,
  			  public translateService: TranslateService) {}

  ngOnInit() {
  }
  
  ngOnDestroy() {
  }
}
