package coop.mnclimbing

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD'])
class PurchaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [purchaseInstanceList: Purchase.list(params), purchaseInstanceTotal: Purchase.count()]
    }

    def create = {
        def purchaseInstance = new Purchase()
        purchaseInstance.properties = params
        return [purchaseInstance: purchaseInstance]
    }

    def save = {
        def purchaseInstance = new Purchase(params)
        if (purchaseInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'purchase.label', default: 'Purchase'), purchaseInstance.id])}"
            redirect(action: "show", id: purchaseInstance.id)
        }
        else {
            render(view: "create", model: [purchaseInstance: purchaseInstance])
        }
    }

    def show = {
        def purchaseInstance = Purchase.get(params.id)
        if (!purchaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'purchase.label', default: 'Purchase'), params.id])}"
            redirect(action: "list")
        }
        else {
            [purchaseInstance: purchaseInstance]
        }
    }

    def edit = {
        def purchaseInstance = Purchase.get(params.id)
        if (!purchaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'purchase.label', default: 'Purchase'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [purchaseInstance: purchaseInstance]
        }
    }

    def update = {
        def purchaseInstance = Purchase.get(params.id)
        if (purchaseInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (purchaseInstance.version > version) {
                    
                    purchaseInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'purchase.label', default: 'Purchase')] as Object[], "Another user has updated this Purchase while you were editing")
                    render(view: "edit", model: [purchaseInstance: purchaseInstance])
                    return
                }
            }
            purchaseInstance.properties = params
            if (!purchaseInstance.hasErrors() && purchaseInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'purchase.label', default: 'Purchase'), purchaseInstance.id])}"
                redirect(action: "show", id: purchaseInstance.id)
            }
            else {
                render(view: "edit", model: [purchaseInstance: purchaseInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'purchase.label', default: 'Purchase'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def purchaseInstance = Purchase.get(params.id)
        if (purchaseInstance) {
            try {
                purchaseInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'purchase.label', default: 'Purchase'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'purchase.label', default: 'Purchase'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'purchase.label', default: 'Purchase'), params.id])}"
            redirect(action: "list")
        }
    }
}
