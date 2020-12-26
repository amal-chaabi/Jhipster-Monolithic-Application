export interface IDoctor {
  id?: number;
  lastname?: string;
  firstname?: string;
  title?: string;
  experience?: string;
  age?: number;
  typeOfSpeciality?: string;
}

export class Doctor implements IDoctor {
  constructor(
    public id?: number,
    public lastname?: string,
    public firstname?: string,
    public title?: string,
    public experience?: string,
    public age?: number,
    public typeOfSpeciality?: string
  ) {}
}
