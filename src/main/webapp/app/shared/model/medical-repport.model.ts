import { IPatient } from 'app/shared/model/patient.model';

export interface IMedicalRepport {
  id?: number;
  typeOfRepport?: string;
  result?: string;
  medicalRepportPatient?: IPatient;
}

export class MedicalRepport implements IMedicalRepport {
  constructor(public id?: number, public typeOfRepport?: string, public result?: string, public medicalRepportPatient?: IPatient) {}
}
