package gunna

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MedRecordController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MedRecord.list(params), model:[medRecordCount: MedRecord.count()]
    }

    def show(MedRecord medRecord) {
        respond medRecord
    }

    def create() {
        respond new MedRecord(params)
    }

    @Transactional
    def save(MedRecord medRecord) {
        if (medRecord == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (medRecord.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond medRecord.errors, view:'create'
            return
        }

        medRecord.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'medRecord.label', default: 'MedRecord'), medRecord.id])
                redirect medRecord
            }
            '*' { respond medRecord, [status: CREATED] }
        }
    }

    def edit(MedRecord medRecord) {
        respond medRecord
    }

    @Transactional
    def update(MedRecord medRecord) {
        if (medRecord == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (medRecord.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond medRecord.errors, view:'edit'
            return
        }

        medRecord.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'medRecord.label', default: 'MedRecord'), medRecord.id])
                redirect medRecord
            }
            '*'{ respond medRecord, [status: OK] }
        }
    }

    @Transactional
    def delete(MedRecord medRecord) {

        if (medRecord == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        medRecord.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'medRecord.label', default: 'MedRecord'), medRecord.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'medRecord.label', default: 'MedRecord'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
