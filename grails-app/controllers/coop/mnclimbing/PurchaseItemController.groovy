package coop.mnclimbing

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOARD'])
class PurchaseItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [purchaseItemInstanceList: PurchaseItem.list(params), purchaseItemInstanceTotal: PurchaseItem.count()]
    }

    def create = {
        def purchaseItemInstance = new PurchaseItem()
        purchaseItemInstance.properties = params
        return [purchaseItemInstance: purchaseItemInstance]
    }

    def save = {
        def purchaseItemInstance = new PurchaseItem(params)
        if (purchaseItemInstance.save(flush: true)) {
            flash.message = "Item Added to Purchase"
            redirect(controller: "purchase", action: "edit", id: purchaseItemInstance.purchase.id)
        }
        else {
            render(view: "create", model: [purchaseItemInstance: purchaseItemInstance])
        }
    }

    def show = {
        def purchaseItemInstance = PurchaseItem.get(params.id)
        if (!purchaseItemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'purchaseItem.label', default: 'PurchaseItem'), params.id])}"
            redirect(action: "list")
        }
        else {
            [purchaseItemInstance: purchaseItemInstance]
        }
    }

    def edit = {
        def purchaseItemInstance = PurchaseItem.get(params.id)
        if (!purchaseItemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'purchaseItem.label', default: 'PurchaseItem'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [purchaseItemInstance: purchaseItemInstance]
        }
    }

    def update = {
        def purchaseItemInstance = PurchaseItem.get(params.id)
        if (purchaseItemInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (purchaseItemInstance.version > version) {
                    
                    purchaseItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'purchaseItem.label', default: 'PurchaseItem')] as Object[], "Another user has updated this PurchaseItem while you were editing")
                    render(view: "edit", model: [purchaseItemInstance: purchaseItemInstance])
                    return
                }
            }
            purchaseItemInstance.properties = params
            if (!purchaseItemInstance.hasErrors() && purchaseItemInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'purchaseItem.label', default: 'PurchaseItem'), purchaseItemInstance.id])}"
                redirect(action: "show", id: purchaseItemInstance.id)
            }
            else {
                render(view: "edit", model: [purchaseItemInstance: purchaseItemInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'purchaseItem.label', default: 'PurchaseItem'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def purchaseItemInstance = PurchaseItem.get(params.id)
        if (purchaseItemInstance) {
            try {
                purchaseItemInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'purchaseItem.label', default: 'PurchaseItem'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'purchaseItem.label', default: 'PurchaseItem'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'purchaseItem.label', default: 'PurchaseItem'), params.id])}"
            redirect(action: "list")
        }
    }
}
