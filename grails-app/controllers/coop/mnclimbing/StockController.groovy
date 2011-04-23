package coop.mnclimbing

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD'])
class StockController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {

		def stockTotal = 0
		Stock.list().each{
			stockTotal += it.amount
		}

        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [stockInstanceList: Stock.list(params), stockInstanceTotal: Stock.count(), stockTotal: stockTotal]
    }

    def create = {
        def stockInstance = new Stock()
        stockInstance.properties = params
        return [stockInstance: stockInstance]
    }

    def save = {
        def stockInstance = new Stock(params)
        if (stockInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'stock.label', default: 'Stock'), stockInstance.id])}"
			redirect(controller:"person", action: "edit", fragment:"tab-stock", id: stockInstance.person.id)
        }
        else {
            render(view: "create", model: [stockInstance: stockInstance])
        }
    }

    def show = {
        def stockInstance = Stock.get(params.id)
        if (!stockInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])}"
            redirect(action: "list")
        }
        else {
            [stockInstance: stockInstance]
        }
    }

    def edit = {
        def stockInstance = Stock.get(params.id)
        if (!stockInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [stockInstance: stockInstance]
        }
    }

    def update = {
        def stockInstance = Stock.get(params.id)
        if (stockInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (stockInstance.version > version) {
                    
                    stockInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'stock.label', default: 'Stock')] as Object[], "Another user has updated this Stock while you were editing")
                    render(view: "edit", model: [stockInstance: stockInstance])
                    return
                }
            }
            stockInstance.properties = params
            if (!stockInstance.hasErrors() && stockInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'stock.label', default: 'Stock'), stockInstance.id])}"
                redirect(controller:"person", action: "edit", fragment:"tab-stock", id: stockInstance.person.id)
            }
            else {
                render(view: "edit", model: [stockInstance: stockInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def stockInstance = Stock.get(params.id)
        if (stockInstance) {
            try {
                stockInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])}"
            redirect(action: "list")
        }
    }
}
